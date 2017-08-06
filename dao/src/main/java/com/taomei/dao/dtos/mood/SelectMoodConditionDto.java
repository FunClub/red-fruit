package com.taomei.dao.dtos.mood;

/**
 * 查询心情的条件
 */
public class SelectMoodConditionDto {
    /**
     * 是否是情侣间查询
     */
    private boolean byHalf;
    /**
     * 通过userId判断心情是否点赞
     */
    private String userId;

    private String halfId;

    private int pageIndex;

    private int pageSize;

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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
