package com.taomei.service.discussion.service;

import com.taomei.dao.dtos.discussion.*;
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

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BaseDiscussionService implements IDiscussionService{

    private final DiscussionRepository discussionRepository;
    private final UserMapper userMapper;
    private final MongoOperations mongoOperations;

    private final INoticeArtService noticeArtService;
    @Autowired
    public BaseDiscussionService(DiscussionRepository discussionRepository, UserMapper userMapper,
                                 MongoOperations mongoOperations, @Qualifier("baseNoticeArtService") INoticeArtService noticeArtService) {
        this.discussionRepository = discussionRepository;
        this.userMapper = userMapper;
        this.mongoOperations = mongoOperations;
        this.noticeArtService = noticeArtService;
    }

    /**
     * 删除评论
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
     * @param dto 插入子评论dto
     * @return 子评论dto
     */
    @Override
    public ShowSubDiscussionDto insertSubDiscussion(InsertSubDiscussionDto dto) throws Exception {
        Query query = Query.query(where("discussionId").is(dto.getDiscussionId()));
        SubDiscussion subDiscussion= dto.getSubDiscussion();
        subDiscussion.setDate(TimeUtil.getSimpleTime());
        Update update = new Update();
        update.addToSet("subDiscussions",subDiscussion);
        int count=mongoOperations.updateFirst(query,update,ParentDiscussion.class,"discussion").getN();

        /*在父级维护子评论数量便于显示最热评论*/
        update = new Update();
        update.inc("subDiscussionsLength",1);
        count+=mongoOperations.updateFirst(query,update,ParentDiscussion.class,"discussion").getN();
        if(count<2)throw new Exception("插入子评论失败");

        /*插入动态通知*/

        return DiscussionUtil.generateSubDiscussionDto(subDiscussion,userMapper);
    }

    /**
     * 插入父级评论
     * @param dto 评论
     * @return 父评论DTO
     */
    @Override
    public ShowParentDiscussionDto insertParentDiscussion(InsertParentDiscussionDto dto) throws Exception {
        ParentDiscussion discussion = dto.getParentDiscussion();
        NoticeArt noticeArt = dto.getNoticeArt();
        discussion.setDate(TimeUtil.getSimpleTime());
        noticeArt.setDate(TimeUtil.getSimpleTime());
       ParentDiscussion parentDiscussion= discussionRepository.insert(discussion);

       if(parentDiscussion!=null){
           noticeArt.setDiscussion(parentDiscussion);
           boolean res = noticeArtService.insertNoticeArt(noticeArt);
           if(res){
               return DiscussionUtil.generateParentDiscussionDto(parentDiscussion,userMapper,discussion.getUserId());
           }
           throw new Exception("插入通知动态失败");
       }

       throw new Exception("插入评论失败");
    }

    /**
     * 查询评论
     * @param dto 查询条件dto
     * @return 分好页的dto
     */
    @Override
    public ShowPagedDiscussionDto selectDiscussion(SelectDiscussionConditionDto dto) {
        Sort sortBy=null;
        if (dto.getSortBy().equals(SortBy.HOT.getSort())){
            sortBy=new Sort(Sort.Direction.DESC,"subDiscussionsLength");
        }
        ParentDiscussion parentDiscussion = new ParentDiscussion();
        parentDiscussion.setArtId(dto.getArtId());
        PageRequest pageRequest= new PageRequest(dto.getPageIndex(),dto.getPageSize(),sortBy);
        Page<ParentDiscussion> discussionPage=discussionRepository.findAll(Example.of(parentDiscussion),pageRequest);
        return DiscussionUtil.showPagedDiscussionDto(discussionPage, userMapper,dto.getUserId());
    }

    /**
     * 给评论点赞
     * @param userId 用户id
     * @param discussionId 评论id
     * @return
     */
    @Override
    public boolean updateThumbsUpUserIds(String userId, String discussionId) {
        Query query =Query.query(where("discussionId").is(discussionId));
        Update update = new Update();
        update.addToSet("thumbsUpUserIds",userId);
        mongoOperations.updateFirst(query,update,ParentDiscussion.class,"discussion");
        return true;
    }
}
