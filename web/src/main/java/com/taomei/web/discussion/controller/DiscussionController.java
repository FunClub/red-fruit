package com.taomei.web.discussion.controller;

import com.taomei.dao.dtos.discussion.InsertSubDiscussionDto;
import com.taomei.dao.dtos.discussion.SelectDiscussionConditionDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.service.discussion.iservice.IDiscussionService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    private final IDiscussionService discussionService;

    @Autowired
    public DiscussionController(@Qualifier("baseDiscussionService") IDiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    /**
     * 插入子评论
     * @param userId 用户id
     * @param dto 插入子评论dto
     * @return
     * @throws Exception
     */
    @PostMapping("/sub-discussion")
    @SetUserId
    public ResultView insertSubDiscussion(String userId, @RequestBody InsertSubDiscussionDto dto) throws Exception {
        dto.setUserId(userId);
        return ResultViewUtil.success(discussionService.insertSubDiscussion(dto));
    }

    /**
     * 添加父级评论
     * @param userId 用户id
     * @param discussion 评论
     * @return 统一数据对象
     * @throws Exception
     */
    @SetUserId
    @PostMapping("/parent-discussion")
    public ResultView insertParentDiscussion(String userId,@RequestBody ParentDiscussion discussion) throws Exception {
        discussion.setUserId(userId);
        return ResultViewUtil.success(discussionService.insertParentDiscussion(discussion));
    }

    /**
     * 查询评论
     * @param dto 查询条件
     * @return 统一数据对象
     */
    @PostMapping("/")
    @SetUserId
    public ResultView selectDiscussion(String userId,@RequestBody SelectDiscussionConditionDto dto){
        dto.setUserId(userId);
        return ResultViewUtil.success(discussionService.selectDiscussion(dto));
    }

    /**
     * 给评论点赞
     * @param userId 用户id
     * @param discussionId 评论ID
     * @return 统一数据对象
     */
    @PutMapping("/{discussionId}/thumbsUpUserIds")
    @SetUserId
    public ResultView updateThumbsUpUserIds(String userId, @PathVariable("discussionId") String discussionId){
        return ResultViewUtil.success(discussionService.updateThumbsUpUserIds(userId,discussionId));
    }
}
