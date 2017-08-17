package com.taomei.service.share.enums;

/**
 * 显示情侣相册的视图类型
 */
public enum AlbumViewType {
    /**
     * 常规
     */
    COMMON(0),
    /**
     * 分类视图
     */
    SORT(1);
    private int type;

    AlbumViewType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
