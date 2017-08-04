package com.taomei.dao.dtos.mood;

/**
 * 查询心情的条件
 */
public class SelectMoodCondition {
    /**
     * 是否是情侣间查询
     */
    private boolean byHalf;

    private String halfId;

    private int page;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
