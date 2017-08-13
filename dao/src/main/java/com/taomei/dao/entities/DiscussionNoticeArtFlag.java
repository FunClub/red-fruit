package com.taomei.dao.entities;

/**
 * 标记一个评论中是否已经存在某个用户的通知动态
 */
public class DiscussionNoticeArtFlag {
    private String noticeUserId;
    private String noticeArtId;

    public String getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(String noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public String getNoticeArtId() {
        return noticeArtId;
    }

    public void setNoticeArtId(String noticeArtId) {
        this.noticeArtId = noticeArtId;
    }
}
