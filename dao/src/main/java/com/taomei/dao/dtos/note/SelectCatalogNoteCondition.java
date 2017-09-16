package com.taomei.dao.dtos.note;

import com.taomei.dao.dtos.share.PageRequestDto;

/**
 * 查询日志的条件dto
 */
public class SelectCatalogNoteCondition {
    private PageRequestDto pageRequestDto;
    private String halfId;
    private String userId;
    /**
     * 是否显示情侣日志
     */
    private  Boolean showHalfNote;
    /**
     * 是否是情侣间查询
     */
    private Boolean byHalf;
    public PageRequestDto getPageRequestDto() {
        return pageRequestDto;
    }

    public void setPageRequestDto(PageRequestDto pageRequestDto) {
        this.pageRequestDto = pageRequestDto;
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

    public Boolean getShowHalfNote() {
        return showHalfNote;
    }

    public void setShowHalfNote(Boolean showHalfNote) {
        this.showHalfNote = showHalfNote;
    }

    public Boolean getByHalf() {
        return byHalf;
    }

    public void setByHalf(Boolean byHalf) {
        this.byHalf = byHalf;
    }
}
