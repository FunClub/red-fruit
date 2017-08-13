package com.taomei.service.share.utils;

import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.discussion.ShowPagedDiscussionDto;
import com.taomei.dao.dtos.discussion.ShowParentDiscussionDto;
import com.taomei.dao.dtos.discussion.ShowSubDiscussionDto;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.entities.discussion.SubDiscussion;
import com.taomei.dao.mapper.UserMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class DiscussionUtil {
    public static ShowPagedDiscussionDto showPagedDiscussionDto(Page<ParentDiscussion> discussionPage, UserMapper userMapper,String userId){
        ShowPagedDiscussionDto pagedDiscussionDto = new ShowPagedDiscussionDto();
        pagedDiscussionDto.setTotalElements(discussionPage.getTotalElements());
        List<ShowParentDiscussionDto> parentDiscussionDtos = new ArrayList<>();

        for(ParentDiscussion parentDiscussion:discussionPage){
            /*生成父评论dto*/
            ShowParentDiscussionDto parentDiscussionDto = generateParentDiscussionDto(parentDiscussion,userMapper,userId);

            parentDiscussionDtos.add(parentDiscussionDto);
        }
        pagedDiscussionDto.setContent(parentDiscussionDtos);
        return pagedDiscussionDto;
    }

    /**
     * 生成父级评论
     * @param parentDiscussion 父评论文档
     * @param userMapper
     * @param userId 用户id
     * @return
     */
    public static ShowParentDiscussionDto generateParentDiscussionDto(ParentDiscussion parentDiscussion,UserMapper userMapper,String userId){

        ShowParentDiscussionDto parentDiscussionDto = new ShowParentDiscussionDto();
        List<ShowSubDiscussionDto> showSubDiscussionDtos = new ArrayList<>();
            /*时间处理*/
        String date =parentDiscussion.getDate();
        String howLongDate = TimeUtil.calculateHowLongAgo(date);
        String sortDate = TimeUtil.calculateSortDate(date);
        String shortDate = TimeUtil.generateShortDate(date);
        parentDiscussionDto.setShortDate(shortDate);
        parentDiscussionDto.setSortDate(sortDate);

            /*基本信息*/
        parentDiscussionDto.setDiscussionId(parentDiscussion.getDiscussionId());
        parentDiscussionDto.setContent(parentDiscussion.getContent());
        parentDiscussionDto.setUserId(parentDiscussion.getUserId());
        UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(parentDiscussion.getUserId());
        parentDiscussionDto.setProfileImg(npInfoDto.getProfileImg());
        parentDiscussionDto.setNickname(npInfoDto.getNickname());


            /*能否评论和评论数量*/
        List<String> thumbsUpUserIds=parentDiscussion.getThumbsUpUserIds();
        if(thumbsUpUserIds!=null){
            parentDiscussionDto.setThumbsUpAble(!thumbsUpUserIds.contains(userId));
            parentDiscussionDto.setThumbsUpCount(thumbsUpUserIds.size());
        }else{
            parentDiscussionDto.setThumbsUpAble(true);
            parentDiscussionDto.setThumbsUpCount(0);
        }
        /*子评论*/
        if(parentDiscussion.getSubDiscussionsLength()!=null){
            List<SubDiscussion> subDiscussions= parentDiscussion.getSubDiscussions();
            List<ShowSubDiscussionDto> subDiscussionDtos = new ArrayList<>();
            for (SubDiscussion subDiscussion:subDiscussions){
                subDiscussionDtos.add(generateSubDiscussionDto(subDiscussion,parentDiscussion.getUserId(),userMapper));
            }
            parentDiscussionDto.setSubDiscussionDtos(subDiscussionDtos);
        }
        return parentDiscussionDto;

    }
    /**
     * 生成子评论dto
     * @param subDiscussion 子评论
     * @param userMapper
     * @param parentDiscussionUserId 父评论用户id
     * @return
     */
    public static ShowSubDiscussionDto generateSubDiscussionDto(SubDiscussion subDiscussion,String parentDiscussionUserId,UserMapper userMapper){
        ShowSubDiscussionDto subDiscussionDto=new ShowSubDiscussionDto();

        subDiscussionDto.setContent(subDiscussion.getContent());
        subDiscussionDto.setUserId(subDiscussion.getUserId());
        //时间计算
        String date1 =subDiscussion.getDate();
        subDiscussionDto.setShortDate(TimeUtil.generateShortDate(date1));
        subDiscussionDto.setSortDate(TimeUtil.calculateSortDate(date1));

        //填充昵称和头像
        UserNPInfoDto userNPInfoDto = userMapper.selectUserNPInfo(subDiscussion.getUserId());
        subDiscussionDto.setNickname(userNPInfoDto.getNickname());
        subDiscussionDto.setProfileImg(userNPInfoDto.getProfileImg());

        //子评论目标用户id处理（填充昵称）
        String sendToUserId = subDiscussion.getSendToUserId();
        UserNPInfoDto userNPInfoDto1 = userMapper.selectUserNPInfo(subDiscussion.getSendToUserId());
        subDiscussionDto.setSendToNickname(userNPInfoDto1.getNickname());
        return subDiscussionDto;
    }
}
