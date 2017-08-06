package com.taomei.service.share.utils;

import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.discussion.ShowPagedDiscussionDto;
import com.taomei.dao.dtos.discussion.ShowParentDiscussionDto;
import com.taomei.dao.dtos.discussion.ShowSubDiscussionDto;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class DiscussionUtil {
    public static ShowPagedDiscussionDto showPagedDiscussionDto(Page<ParentDiscussion> discussionPage, UserMapper userMapper){
        ShowPagedDiscussionDto pagedDiscussionDto = new ShowPagedDiscussionDto();
        pagedDiscussionDto.setTotalElements(discussionPage.getTotalElements());
        List<ShowParentDiscussionDto> parentDiscussionDtos = new ArrayList<>();
        ShowParentDiscussionDto parentDiscussionDto=null;
        List<ShowSubDiscussionDto> showSubDiscussionDtos =null;
        for(ParentDiscussion parentDiscussion:discussionPage){
            parentDiscussionDto = new ShowParentDiscussionDto();
            showSubDiscussionDtos = new ArrayList<>();
            /*时间处理*/
            String date =parentDiscussion.getDate();
            String howLongDate = TimeUtil.calculateHowLongAgo(date);
            String sortDate = TimeUtil.calculateSortDate(howLongDate,date);
            String shortDate = TimeUtil.generateShortDate(date);
            parentDiscussionDto.setShortDate(shortDate);
            parentDiscussionDto.setSortDate(sortDate);

            /*基本信息*/
            parentDiscussionDto.setDiscussionId(parentDiscussion.getDiscussionId());
            parentDiscussionDto.setContent(parentDiscussion.getContent());
            parentDiscussionDto.setUserId(parentDiscussion.getUserId());
            parentDiscussionDto.setThumbsUpCount(parentDiscussion.getThumbsUpCount());
            UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(parentDiscussion.getUserId());
            parentDiscussionDto.setProfileImg(npInfoDto.getProfileImg());
            parentDiscussionDto.setNickName(npInfoDto.getNickname());

            /*子评论*/
            if(parentDiscussion.getSubDiscussionsLength()!=0){

            }
            parentDiscussionDtos.add(parentDiscussionDto);
        }
        pagedDiscussionDto.setContent(parentDiscussionDtos);
        return pagedDiscussionDto;
    }
}
