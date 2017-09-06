package com.taomei.dao.dtos.circle;

import com.taomei.dao.dtos.base.PageRequestDto;

/**
 * 查询帖子条件dto
 */
public class SelectPostConditionDto extends PageRequestDto{
    private String circleName;
    private String userId;

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
