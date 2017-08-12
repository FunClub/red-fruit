package com.taomei.service.share.enums;

/**
 * 动态类型
 */
public enum  ArtType {
    MOOD("心情");
    private String type;

    ArtType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
