package com.taomei.service.share.enums;

/**
 * 相册排序类型
 */
public enum AlbumSortType {
    /**
     * 最近更新的相册在前
     */
    LATEST_UPLOAD(0),

    /**
     * 最新创建的相册在前
     */
    LATEST_CREATE(1),

    /**
     * 最后创建的相册在前
     */
    LAST_CREATE(2)
    ;
    private int type;

    AlbumSortType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
