package com.taomei.dao.dtos.noticeart;

import java.util.List;

/**
 * 用于显示已经分页的用户动态通知
 */
public class ShowPagedNoticeArtDto {
    private  long totalElements;
    private List<ShowNoticeArtDto> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<ShowNoticeArtDto> getContent() {
        return content;
    }

    public void setContent(List<ShowNoticeArtDto> content) {
        this.content = content;
    }
}
