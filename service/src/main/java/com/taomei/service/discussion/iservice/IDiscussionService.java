package com.taomei.service.discussion.iservice;

import com.taomei.dao.entities.discussion.ParentDiscussion;

/**
 * 评论服务接口
 */
public interface IDiscussionService {

    /**
     * 插入父级评论
     * @param discussion 评论
     * @return 成功或失败
     */
    boolean insertParentDiscussion(ParentDiscussion discussion);
}
