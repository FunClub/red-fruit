package com.taomei.dao.dtos.base;

/**
 * 情侣Id和用户id dto
 */
public class IdsDto {
    private String userId;
    private String halfId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }
}
