package com.taomei.web.circle.controller;

import com.taomei.dao.dtos.circle.SelectPostConditionDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.circle.Post;
import com.taomei.service.circle.iservice.ICircleService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/circle")
public class CircleController {
    private final ICircleService circleService;

    @Autowired
    public CircleController(@Qualifier("baseCircleService") ICircleService circleService) {
        this.circleService = circleService;
    }

    /**
     * 查询帖子
     * @param userId 用户id
     * @param dto 查询帖子dto
     * @return 统一数据对象
     */
    @PostMapping("/posts")
    @SetUserId
    public ResultView selectPosts(String userId,@RequestBody SelectPostConditionDto dto){
        dto.setUserId(userId);
        return ResultViewUtil.success(circleService.selectPosts(dto));
    }
    /**
     * 添加一个帖子
     * @param userId 用户id
     * @param post 帖子
     * @return 统一数据对象
     */
    @PostMapping("/post")
    @SetUserId
    public ResultView insertPost(String userId,@RequestBody Post post){
        post.setUserId(userId);
        return ResultViewUtil.success(circleService.insertPost(post));
    }
}
