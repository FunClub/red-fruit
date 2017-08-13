package com.taomei.service.discussion.service;

import com.taomei.dao.dtos.discussion.*;
import com.taomei.dao.entities.DiscussionNoticeArtFlag;
import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.entities.discussion.SubDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.dao.repository.NoticeArtRepository;
import com.taomei.service.discussion.iservice.IDiscussionService;
import com.taomei.service.noticeart.iservice.INoticeArtService;
import com.taomei.service.share.enums.SortBy;
import com.taomei.service.share.utils.DiscussionUtil;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BaseDiscussionService implements IDiscussionService {

    private final DiscussionRepository discussionRepository;
    private final UserMapper userMapper;
    private final MongoOperations mongoOperations;
    private final NoticeArtRepository noticeArtRepository;
    private final INoticeArtService noticeArtService;

    @Autowired
    public BaseDiscussionService(DiscussionRepository discussionRepository, UserMapper userMapper,
                                 MongoOperations mongoOperations, @Qualifier("baseNoticeArtService") INoticeArtService noticeArtService, NoticeArtRepository noticeArtRepository) {
        this.discussionRepository = discussionRepository;
        this.userMapper = userMapper;
        this.mongoOperations = mongoOperations;
        this.noticeArtService = noticeArtService;
        this.noticeArtRepository = noticeArtRepository;
    }

    /**
     * 删除评论
     *
     * @param discussionId 评论id
     * @return
     */
    @Override
    public boolean deleteParentDiscussion(String discussionId) {
        discussionRepository.delete(discussionId);
        return true;
    }

    /**
     * 插入子评论
     *
     * @param dto 插入子评论dto
     * @return 子评论dto
     */
    @Override
    public ShowSubDiscussionDto insertSubDiscussion(InsertSubDiscussionDto dto) throws Exception {
        /*更新动态通知*/
        ParentDiscussion parentDiscussion = discussionRepository.findOne(dto.getDiscussionId());
        List<DiscussionNoticeArtFlag> flags = parentDiscussion.getNoticeArtFlags();
        //判断子评论目标是否已经有了该评论的通知动态没有就添加，有就取出通知动态id，更新通知动态，反之创建通知动态
        String noticeArtId = null;
        for (DiscussionNoticeArtFlag flag : flags) {
            if (flag.getNoticeUserId().equals(dto.getSubDiscussion().getSendToUserId())) {
                noticeArtId = flag.getNoticeArtId();
            }
        }
        SubDiscussion subDiscussion = dto.getSubDiscussion();
        NoticeArt oldNoticeArt=dto.getNoticeArt();
        NoticeArt noticeArt = null;
        DiscussionNoticeArtFlag newFlg = null;
        if (noticeArtId == null) {//创建通知动态及其标志
            oldNoticeArt .setDate(TimeUtil.getSimpleTime());
            oldNoticeArt.setDiscussionId(dto.getDiscussionId());
            oldNoticeArt.setCurrentContent(subDiscussion.getContent());
            noticeArt = noticeArtService.insertNoticeArt(dto.getNoticeArt());
            if(noticeArt==null)throw new Exception("插入动态通知失败");
            newFlg = new DiscussionNoticeArtFlag();
            newFlg.setNoticeArtId(noticeArt.getNoticeArtId());
            newFlg.setNoticeUserId(dto.getSubDiscussion().getSendToUserId());
        } else {//更新通知的动态
            //如果子评论评论的目标是通知动态中的通知动态用户就更新此动态通知
            Query query1 = Query.query(where("noticeArtId").is(noticeArtId));
            Update update1 = new Update();
            update1.set("date", TimeUtil.getSimpleTime())
                    .set("generateUserId", subDiscussion.getUserId())
                    .set("currentContent", subDiscussion.getContent())
                    .set("state", false);
            mongoOperations.updateFirst(query1, update1, NoticeArt.class, "noticeArt");
        }
        //添加子评论和标记
        Query query = Query.query(where("discussionId").is(dto.getDiscussionId()));
        subDiscussion.setDate(TimeUtil.getSimpleTime());
        Update update = new Update();
        update.addToSet("subDiscussions", subDiscussion);
        if (newFlg != null) {
            update.addToSet("noticeArtFlags", newFlg);
        }
        int count = mongoOperations.updateFirst(query, update, ParentDiscussion.class, "discussion").getN();

        /*在父级维护子评论数量便于显示最热评论*/
        update = new Update();
        update.inc("subDiscussionsLength", 1);
        count += mongoOperations.updateFirst(query, update, ParentDiscussion.class, "discussion").getN();
        if (count < 2) throw new Exception("插入子评论失败");
        return DiscussionUtil.generateSubDiscussionDto(subDiscussion, parentDiscussion.getUserId(), userMapper);
    }

    /**
     * 插入父级评论
     *
     * @param dto 评论
     * @return 父评论DTO
     */
    @Override
    public ShowParentDiscussionDto insertParentDiscussion(InsertParentDiscussionDto dto) throws Exception {
        ParentDiscussion discussion = dto.getParentDiscussion();
        discussion.setDate(TimeUtil.getSimpleTime());
        ParentDiscussion parentDiscussion = discussionRepository.insert(discussion);
        //插入动态通知
        if (parentDiscussion != null) {
            NoticeArt noticeArt = dto.getNoticeArt();
            noticeArt.setDate(TimeUtil.getSimpleTime());
            noticeArt.setDiscussionId(parentDiscussion.getDiscussionId());
            noticeArt.setCurrentContent(dto.getParentDiscussion().getContent());
            noticeArt = noticeArtService.insertNoticeArt(noticeArt);

            //将通知动态id和用户id设置到评论(当评论变化时更新通知动态)
            Query query = Query.query(where("discussionId").is(parentDiscussion.getDiscussionId()));
            Update update = new Update();
            DiscussionNoticeArtFlag flag = new DiscussionNoticeArtFlag();
            flag.setNoticeUserId(dto.getNoticeArt().getNoticeArtUserId());
            flag.setNoticeArtId(noticeArt.getNoticeArtId());
            update.addToSet("noticeArtFlags", flag)
                    .set("currentConten",discussion.getContent());
            mongoOperations.updateFirst(query, update, ParentDiscussion.class, "discussion");
            return DiscussionUtil.generateParentDiscussionDto(parentDiscussion, userMapper, discussion.getUserId());
        }
        throw new Exception("插入评论失败");
    }

    /**
     * 查询评论
     *
     * @param dto 查询条件dto
     * @return 分好页的dto
     */
    @Override
    public ShowPagedDiscussionDto selectDiscussion(SelectDiscussionConditionDto dto) {
        Sort sortBy = null;
        if (dto.getSortBy().equals(SortBy.HOT.getSort())) {
            sortBy = new Sort(Sort.Direction.DESC, "subDiscussionsLength");
        }
        ParentDiscussion parentDiscussion = new ParentDiscussion();
        parentDiscussion.setArtId(dto.getArtId());
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(), dto.getPageSize(), sortBy);
        Page<ParentDiscussion> discussionPage = discussionRepository.findAll(Example.of(parentDiscussion), pageRequest);
        return DiscussionUtil.showPagedDiscussionDto(discussionPage, userMapper, dto.getUserId());
    }

    /**
     * 给评论点赞
     *
     * @param userId       用户id
     * @param discussionId 评论id
     * @return
     */
    @Override
    public boolean updateThumbsUpUserIds(String userId, String discussionId) {
        Query query = Query.query(where("discussionId").is(discussionId));
        Update update = new Update();
        update.addToSet("thumbsUpUserIds", userId);
        mongoOperations.updateFirst(query, update, ParentDiscussion.class, "discussion");
        return true;
    }
}
