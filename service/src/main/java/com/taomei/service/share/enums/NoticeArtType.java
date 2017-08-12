package com.taomei.service.share.enums;

/**
 * 通知动态类型
 */
public enum NoticeArtType {
    /**
     * 给动态点赞
     */
    THUMBSUP("赞"),

    /**
     * 评论
     */
    DISCUSSION("评论"),

    REPLY("回复");
    private String type;

    NoticeArtType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
