package com.taomei.service.discussion.iservice;

import com.taomei.dao.dtos.discussion.*;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import org.springframework.data.domain.Page;

/**
 * 评论服务接口
 */
public interface IDiscussionService {

    /**
     * 插入子评论
     * @param dto 插入子评论dto
     * @return 成功与否
     */
    ShowSubDiscussionDto insertSubDiscussion(InsertSubDiscussionDto dto) throws Exception;
    /**
     * 插入父级评论
     * @param discussion 评论
     * @return 父评论DTO
     */
    ShowParentDiscussionDto insertParentDiscussion(ParentDiscussion discussion) throws Exception;

    /**
     * 查询评论
     * @param dto 查询条件dto
     * @return 分好页的dto
     */
    ShowPagedDiscussionDto selectDiscussion(SelectDiscussionConditionDto dto);

    /**
     * 点赞
     * @param userId 用户id
     * @param discussionId 评论id
     * @return 成功与否
     */
    boolean updateThumbsUpUserIds(String userId,String discussionId);

}
