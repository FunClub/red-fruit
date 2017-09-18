package com.taomei.dao.dtos.email;

import com.taomei.dao.dtos.share.PageRequestDto;
/**
 *
 */
public class SelectEmailCatalogDto extends PageRequestDto {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
