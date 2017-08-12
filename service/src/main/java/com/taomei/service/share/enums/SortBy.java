package com.taomei.service.share.enums;

/**
 * 数据排序类型
 */
public enum SortBy {
    LATEST("date"),
    HOT("subDiscussionsLength");
    private String sort;

    SortBy(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
