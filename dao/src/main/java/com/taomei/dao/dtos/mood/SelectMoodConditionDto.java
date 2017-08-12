package com.taomei.dao.dtos.mood;

import com.taomei.dao.dtos.base.PageRequest;

/**
 * 查询心情的条件
 */
public class SelectMoodConditionDto extends PageRequest{
    /**
     * 是否是情侣间查询
     */
    private boolean byHalf;
    /**
     * 通过userId判断心情是否点赞
     */
    private String userId;

    private String halfId;


    public boolean isByHalf() {
        return byHalf;
    }

    public void setByHalf(boolean byHalf) {
        this.byHalf = byHalf;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
