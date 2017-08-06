package com.taomei.web.discussion.controller;

import com.taomei.dao.dtos.discussion.SelectDiscussionConditionDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.service.discussion.iservice.IDiscussionService;
import com.taomei.web.share.contolleradvice.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    private final IDiscussionService discussionService;

    @Autowired
    public DiscussionController(@Qualifier("baseDiscussionService") IDiscussionService discussionService) {
        this.discussionService = discussionService;
    }
    @SetUserId
    @PostMapping("/parent-discussion")
    public ResultView insertParentDiscussion(String userId,@RequestBody ParentDiscussion discussion) throws Exception {
        discussion.setUserId(userId);
        return ResultViewUtil.success(discussionService.insertParentDiscussion(discussion));
    }

    @PostMapping("/")
    public ResultView selectDiscussion(@RequestBody SelectDiscussionConditionDto dto){
        return ResultViewUtil.success(discussionService.selectDiscussion(dto));
    }
}
