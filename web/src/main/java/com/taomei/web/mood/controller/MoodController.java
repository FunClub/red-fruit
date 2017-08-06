package com.taomei.web.mood.controller;

import com.taomei.dao.dtos.mood.SelectMoodConditionDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.mood.iservice.IMoodService;
import com.taomei.web.share.contolleradvice.anotaions.SetHalfId;
import com.taomei.web.share.contolleradvice.anotaions.SetId;
import com.taomei.web.share.contolleradvice.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import com.taomei.web.share.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 心情控制器
 */
@RestController
@RequestMapping("/mood")
public class MoodController {
    private final IMoodService moodService;
    private final HttpSession session;
    @Autowired
    public MoodController(@Qualifier("baseMoodService") IMoodService moodService, HttpSession session) {
        this.moodService = moodService;
        this.session=session;
    }

    /**
     * 插入一条心情
     * @param mood 心情对象
     * @return 统一数据对象
     * @throws Exception 插入失败
     */
    @SetId
    @PostMapping("/")
    public ResultView insertMood(String userId,String halfId,@RequestBody Mood mood) throws Exception {
        mood.setHalfId(halfId);
        mood.setUserId(userId);
        return ResultViewUtil.success(moodService.insert(mood));
    }

    /**
     * 根据条件查询心情
     * @param session
     * @param byHalf 是否是情侣间查询
     * @param pageIndex 第几页
     * @param pageSize 每一页多少条数据
     * @return  统一数据对象
     */
    @SetId
    @GetMapping("/{byHalf}/{pageIndex}/{pageSize}")
    public ResultView getMood(String userId,String halfId,@PathVariable("byHalf") boolean byHalf, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize,HttpSession session) {
        SelectMoodConditionDto condition = new SelectMoodConditionDto();
        condition.setByHalf(byHalf);condition.setPageIndex(pageIndex);condition.setPageSize(pageSize);condition.setUserId(userId);
        //情侣间查询用情侣id查
        if(condition.isByHalf()){
            condition.setHalfId(halfId);
        }
        return ResultViewUtil.success(moodService.selectMood(condition));
    }

    /**
     * 点赞
     * @param userId 用户ID
     * @param moodId 心情ID
     * @return 统一数据对象
     */
    @PutMapping("/{moodId}/thumbsUpUserIds")
    @SetUserId
    public  ResultView updateThumbsUpUserIds(String userId, @PathVariable("moodId") String moodId){
        return ResultViewUtil.success(moodService.updateThumbsUpUserIds(userId,moodId));
    }
}
