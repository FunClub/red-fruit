package com.taomei.dao.dtos.discussion;

/**
 * 插入子评论的dto
 */
public class InsertSubDiscussionDto {
   private String discussionId;
   private String contend;
   private String date;
   private String userId;
    /**
     * 给自评论回复的用户id(回复谁，为空则是回复父评论)
     */
    private String sendToUserId;

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getContend() {
        return contend;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContend(String contend) {
        this.contend = contend;
    }

    public String getSendToUserId() {
        return sendToUserId;
    }

    public void setSendToUserId(String sendToUserId) {
        this.sendToUserId = sendToUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
