package com.taomei.web.discussion.controller;

import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.service.discussion.iservice.IDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("discussion")
public class DiscussionController {
    @Autowired
    @Qualifier("baseDiscussionService")
    private IDiscussionService discussionService;

    @PostMapping("/parent-discussion")
    public void insertParentDiscussion(HttpSession session, @RequestBody ParentDiscussion discussion){

    }
}
