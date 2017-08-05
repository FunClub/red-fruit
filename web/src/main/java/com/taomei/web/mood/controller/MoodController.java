package com.taomei.web.mood.controller;

import com.taomei.dao.dtos.mood.SelectMoodConditionDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.mood.iservice.IMoodService;
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
    @Autowired
    public MoodController(@Qualifier("baseMoodService") IMoodService moodService) {
        this.moodService = moodService;
    }

    /**
     * 插入一条心情
     * @param mood 心情对象
     * @param session
     * @return 统一数据对象
     * @throws Exception 插入失败
     */
    @PostMapping("/")
    public ResultView insertMood(@RequestBody Mood mood, HttpSession session) throws Exception {
        mood.setHalfId(UserUtil.getHalfIdBySession(session));
        mood.setUserId(UserUtil.getUserIdBySession(session));
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
    @GetMapping("/{byHalf}/{pageIndex}/{pageSize}")
    public ResultView getMood(HttpSession session, @PathVariable("byHalf") boolean byHalf, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        SelectMoodConditionDto condition = new SelectMoodConditionDto();
        condition.setByHalf(byHalf);condition.setPageIndex(pageIndex);condition.setPageSize(pageSize);
        //情侣间查询用情侣id查
        if(condition.isByHalf()){
            condition.setHalfId(UserUtil.getHalfIdBySession(session));
        }
        return ResultViewUtil.success(moodService.selectMoodByHalfId(condition));
    }

}
