package com.taomei.service.noticeart.service;

import com.taomei.dao.dtos.share.user.UserNPInfoDto;
import com.taomei.dao.dtos.discussion.ShowParentDiscussionDto;
import com.taomei.dao.dtos.noticeart.DeleteNoticeArtDto;
import com.taomei.dao.dtos.noticeart.SelectNoticeArtConditionDto;
import com.taomei.dao.dtos.noticeart.ShowNoticeArtDto;
import com.taomei.dao.dtos.noticeart.ShowPagedNoticeArtDto;
import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.dao.repository.NoticeArtRepository;

import com.taomei.service.noticeart.iservice.INoticeArtService;
import com.taomei.service.share.utils.DiscussionUtil;
import com.taomei.service.share.utils.TimeUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态通知的基本实现
 */
@Service
public class BaseNoticeArtService implements INoticeArtService {
    private final NoticeArtRepository artRepository;
    private final MongoOperations mongoOperations;
    private final UserMapper userMapper;
    private final DiscussionRepository discussionRepository;
    @Autowired
    public BaseNoticeArtService(NoticeArtRepository artRepository, MongoOperations mongoOperations,
                                UserMapper userMapper, DiscussionRepository discussionRepository) {
        this.artRepository = artRepository;
        this.mongoOperations = mongoOperations;

        this.userMapper = userMapper;
        this.discussionRepository = discussionRepository;
    }

    /**
     * 删除动态通知
     * @param dto
     * @return
     */
    @Override
    public boolean deleteNoticeArt(DeleteNoticeArtDto dto) {
        //删除动态通知
        artRepository.delete(dto.getNoticeArtId());

        //删除动态通知的评论中保存动态通知标记
        int count=0;
        if(dto.getDiscussionId()!=null){//如果删除的动态中有评论
            Document document = new Document();
            document.append("noticeUserId",dto.getNoticeArtUserId());
            document.append("noticeArtId",dto.getNoticeArtId());
            Query query =Query.query(where("discussionId").is(dto.getDiscussionId()));
            Update update = new Update();
            update.pull("noticeArtFlags",document);
            count=mongoOperations.updateFirst(query,update,ParentDiscussion.class,"discussion").getN();
        }
        return count>0;
    }

    /**
     * 查询用户动态信息
     * @param dto 用户id
     * @return 分页的动态信息
     */
    @Override
    public ShowPagedNoticeArtDto selectNoticeArt(SelectNoticeArtConditionDto dto) {
        Sort sort = new Sort(Sort.Direction.DESC,"date");
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(),dto.getPageSize(),sort);
        NoticeArt noticeArtExample = new NoticeArt();
        noticeArtExample.setNoticeArtUserId(dto.getUserId());
        Page<NoticeArt> page=artRepository.findAll(Example.of(noticeArtExample),pageRequest);
        ShowPagedNoticeArtDto pageDto = new ShowPagedNoticeArtDto();
        pageDto.setTotalElements(page.getTotalElements());
        List<ShowNoticeArtDto> noticeArtDtos = new ArrayList<>();
        pageDto.setContent(noticeArtDtos);
        ShowNoticeArtDto noticeArtDto=null;
        for(NoticeArt noticeArt:page){
            noticeArtDto= new ShowNoticeArtDto();
            //设置基本信息
            noticeArtDto.setNoticeArtId(noticeArt.getNoticeArtId());
            noticeArtDto.setGenerateUserId(noticeArt.getGenerateUserId());
            noticeArtDto.setArtType(noticeArt.getArtType());
            noticeArtDto.setArtId(noticeArt.getArtId());
            noticeArtDto.setArtUserId(noticeArt.getArtUserId());
            noticeArtDto.setNoticeArtType(noticeArt.getNoticeArtType());
            UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(noticeArtDto.getGenerateUserId());
            noticeArtDto.setGenerateNickname(npInfoDto.getNickname());
            noticeArtDto.setGenerateProfileImg(npInfoDto.getProfileImg());
            noticeArtDto.setShortDate(TimeUtil.generateShortDate(noticeArt.getDate()));
            noticeArtDto.setSortDate(TimeUtil.calculateSortDate(noticeArt.getDate()));
            noticeArtDto.setArtContent(noticeArt.getArtContent());
            noticeArtDto.setOriginal(noticeArt.getOriginal());
            noticeArtDto.setFirstArtImg(noticeArt.getFirstArtImg());
            noticeArtDto.setCurrentContent(noticeArt.getCurrentContent());
            //填充动态人的昵称,和id
            //如果动态不是原创
            if(!noticeArt.getOriginal()){
                String originalUserId = noticeArt.getOriginalUserId();
                UserNPInfoDto originalUserDto = userMapper.selectUserNPInfo(originalUserId);
                noticeArtDto.setArtNickname(originalUserDto.getNickname());
                noticeArtDto.setOriginalArtId(noticeArt.getOriginalArtId());
                noticeArtDto.setOriginalUserId(noticeArt.getOriginalUserId());
            }else{
                String artUserId = noticeArt.getArtUserId();
                UserNPInfoDto artUserDto = userMapper.selectUserNPInfo(artUserId);
                noticeArtDto.setArtNickname(artUserDto.getNickname());
            }
            ShowParentDiscussionDto discussionDto=null;
            String discussionId = noticeArt.getDiscussionId();
            //如果有评论就设置评论
            if(discussionId!=null){
                ParentDiscussion parentDiscussion=discussionRepository.findOne(noticeArt.getDiscussionId());
                if(parentDiscussion!=null){
                    discussionDto = DiscussionUtil.generateParentDiscussionDto(parentDiscussion,userMapper,dto.getUserId());
                    noticeArtDto.setDiscussion(discussionDto);
                }
            }

            noticeArtDtos.add(noticeArtDto);
            //生成评论
        }
        return pageDto;
    }

    /**
     * 插入动态通知
     * @param noticeArt 通知动态文档
     * @return 成功与否
     */
    @Override
    public NoticeArt insertNoticeArt(NoticeArt noticeArt) {
        return artRepository.insert(noticeArt);
    }
}
