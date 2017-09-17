package com.taomei.service.share.utils;

import com.taomei.dao.dtos.share.user.UserNPInfoDto;
import com.taomei.dao.dtos.mood.ShowMoodDto;
import com.taomei.dao.dtos.mood.ShowPagedMoodDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 心情工具
 */
public class MoodUtils {
    /**
     * 将mongo分页的心情转换为可使用的的MoodDto
     * @param moodPage mongo分页的心情集合
     * @return 分页的心情dto
     */
    public static ShowPagedMoodDto generateShowPagedMoodDto(Page<Mood> moodPage, UserMapper userMapper, String userId, MongoOperations operations){
        ShowPagedMoodDto showPagedMoodDto = new ShowPagedMoodDto();
        List<ShowMoodDto> showMoodDtos = new ArrayList<>();

        showPagedMoodDto.setTotalElements(moodPage.getTotalElements());
        List<Mood> preMoods = moodPage.getContent();
        UserNPInfoDto userNPInfoDto =null;
        ShowMoodDto showMoodDto = null;
        String lastDate="";
        for(Mood mood:preMoods){
            userNPInfoDto = userMapper.selectUserNPInfo(mood.getUserId());
            showMoodDto=  new ShowMoodDto();
            showMoodDto.setMood(mood);
            showMoodDto.setMoodId(mood.getMoodId());
            //设置心情的额外属性
            showMoodDto.setNickname(userNPInfoDto.getNickname());
            showMoodDto.setProfileImg(userNPInfoDto.getProfileImg());
            String howLoginAgo=TimeUtil.calculateHowLongAgo(mood.getDate());
            showMoodDto.setHowLongAgo(howLoginAgo);
            String sortDate = TimeUtil.calculateSortDate(mood.getDate());
            //是否显示时间分类的判断
            if(sortDate.equals(lastDate)){
                showMoodDto.setShowSortDate(false);
            }else{
                showMoodDto.setShowSortDate(true);
                showMoodDto.setSortDate(sortDate);
                lastDate=sortDate;
            }
            showMoodDto.setShortDate(TimeUtil.generateShortDate(mood.getDate()));
            //设置点赞数量,能否点赞等
            List<String> thumbsUpUserIds=mood.getThumbsUpUserIds();
            if(thumbsUpUserIds==null){
                showMoodDto.setThumbsUpAble(true);
                showMoodDto.setThumbsUpCount((long) 0);
            }else{
                showMoodDto.setThumbsUpCount((long) thumbsUpUserIds.size());
                showMoodDto.setThumbsUpAble(!thumbsUpUserIds.contains(userId));
            }
            //清空点赞userId数组,前台不需要
            mood.setThumbsUpUserIds(null);

            //查询评论数
            showMoodDto.setDiscussionCount(operations.count(Query.query(where("artId").is(mood.getMoodId())),
                    ParentDiscussion.class,"discussion"));

            showMoodDtos.add(showMoodDto);
        }
        showPagedMoodDto.setContent(showMoodDtos);
        return showPagedMoodDto;
    }



}
