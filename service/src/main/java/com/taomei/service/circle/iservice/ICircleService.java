package com.taomei.service.circle.iservice;

import com.taomei.dao.entities.circle.Post;


/**
 * 圈子模块服务接口
 */

public interface ICircleService {
    /**
     * 添加帖子
     * @param post 帖子文档
     * @return 新帖子
     */
    Post insertPost(Post post);
}
