package com.taomei.dao.dtos.discussion;

/**
 * 查询评论DTO
 */
public class SelectDiscussionConditionDto {
    /**
     * 动态id
     */
    private String artId;
    private int pageSize;
    private int pageIndex;
    /**
     * 根据什么排序
     */
    private String sortBy;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
