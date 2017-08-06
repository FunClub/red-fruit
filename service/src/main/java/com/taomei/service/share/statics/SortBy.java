package com.taomei.service.share.statics;

/**
 * 数据排序类型
 */
public enum SortBy {
    LATEST("latest"),
    HOT("hot");
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
