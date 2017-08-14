package com.taomei.dao.dtos.noticeart;

/**
 * 删除通知动态的dto
 */
public class DeleteNoticeArtDto {
    private String noticeArtId;
    private String discussionId;
    private String noticeArtUserId;

    public String getNoticeArtId() {
        return noticeArtId;
    }

    public void setNoticeArtId(String noticeArtId) {
        this.noticeArtId = noticeArtId;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getNoticeArtUserId() {
        return noticeArtUserId;
    }

    public void setNoticeArtUserId(String noticeArtUserId) {
        this.noticeArtUserId = noticeArtUserId;
    }
}
