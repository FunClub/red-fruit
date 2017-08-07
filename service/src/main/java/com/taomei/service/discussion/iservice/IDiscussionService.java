package com.taomei.service.discussion.iservice;

import com.taomei.dao.dtos.discussion.InsertSubDiscussionDto;
import com.taomei.dao.dtos.discussion.SelectDiscussionConditionDto;
import com.taomei.dao.dtos.discussion.ShowPagedDiscussionDto;
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
    boolean insertSubDiscussion(InsertSubDiscussionDto dto) throws Exception;
    /**
     * 插入父级评论
     * @param discussion 评论
     * @return 成功或失败
     */
    boolean insertParentDiscussion(ParentDiscussion discussion) throws Exception;

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
