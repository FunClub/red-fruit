package com.taomei.dao.dtos.email;

import com.taomei.dao.dtos.share.ShowPagedArtDto;

/**
 * 邮件分页对象
 */
public class ShowPagedEmailDto<T> extends ShowPagedArtDto {
    private int unReadCount;

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }
}
