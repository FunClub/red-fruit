package com.taomei.dao.dtos.noticeart;

import com.taomei.dao.dtos.share.PageRequestDto;

/**
 * 查询通知动态的dto
 */
public class SelectNoticeArtConditionDto extends PageRequestDto {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
