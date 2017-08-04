package com.taomei.web.mood.controller;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.dtos.mood.SelectMoodCondition;
import com.taomei.dao.entities.Half;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.mood.iservice.IMoodService;
import com.taomei.web.share.utils.ResultViewStatusUtil;
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


    @PostMapping("/")
    public ResultView insertMood(@RequestBody Mood mood, HttpSession session) throws Exception {
        mood.setHalfId(UserUtil.getHalfIdBySession(session));
        mood.setUserId(UserUtil.getUserIdBySession(session));
        return ResultViewUtil.success(moodService.insert(mood));
    }
    @GetMapping("/{byHalf}/{page}/{pageSize}")
    public ResultView getMood(HttpSession session, @PathVariable("byHalf") boolean byHalf, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) throws Exception {
        SelectMoodCondition condition = new SelectMoodCondition();
        condition.setByHalf(byHalf);condition.setPage(page);condition.setPageSize(pageSize);
        if(condition.isByHalf()){
            condition.setHalfId(UserUtil.getHalfIdBySession(session));
        }
        return ResultViewUtil.success(moodService.selectMoodByHalfId(condition));
    }

}
