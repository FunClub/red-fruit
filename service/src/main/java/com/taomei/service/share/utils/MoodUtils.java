package com.taomei.service.share.utils;

import com.taomei.dao.dtos.mood.MoodUserInfoDto;
import com.taomei.dao.dtos.mood.ShowMoodDto;
import com.taomei.dao.dtos.mood.ShowPagedMoodDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.mapper.UserMapper;
import org.springframework.data.domain.Page;

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
    public static ShowPagedMoodDto generateShowPagedMoodDto(Page<Mood> moodPage, UserMapper userMapper){
        ShowPagedMoodDto showPagedMoodDto = new ShowPagedMoodDto();
        List<ShowMoodDto> showMoodDtos = new ArrayList<>();

        showPagedMoodDto.setTotalElements(moodPage.getTotalElements());
        showPagedMoodDto.setTotalPages(moodPage.getTotalPages());
        showPagedMoodDto.setCurrentPage(moodPage.getNumber());
        List<Mood> preMoods = moodPage.getContent();
        MoodUserInfoDto moodUserInfoDto=null;
        ShowMoodDto showMoodDto = null;
        for(Mood mood:preMoods){
            moodUserInfoDto= userMapper.selectMoodUserInfo(mood.getUserId());
            showMoodDto=  new ShowMoodDto();
            showMoodDto.setNickname(moodUserInfoDto.getNickname());
            showMoodDto.setProfileImg(moodUserInfoDto.getProfileImg());
            showMoodDto.setHowLongAgo(TimeUtil.calculateHowLongAgo(mood.getDate()));
            showMoodDto.setMood(mood);
            showMoodDtos.add(showMoodDto);
        }
        showPagedMoodDto.setContent(showMoodDtos);
        return showPagedMoodDto;
    }
}
