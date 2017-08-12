package com.taomei.dao.dtos.discussion;

import com.taomei.dao.dtos.base.PageRequest;

/**
 * 查询评论DTO
 */
public class SelectDiscussionConditionDto extends PageRequest{
    /**
     * 动态id
     */
    private String artId;
    /**
     * 用户id,判断能否点赞
     */
    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }
}
