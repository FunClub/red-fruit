package com.taomei.dao.dtos.discussion;

public class ShowSubDiscussionDto {
    private String userId;
    private String nickname;
    private String profileImg;
    private String content;
    private String sortDate;
    private String shortDate;
    /**
     * 子评论目标用户昵称
     */
    private String sendToNickname;
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSendToNickname() {
        return sendToNickname;
    }

    public void setSendToNickname(String sendToNickname) {
        this.sendToNickname = sendToNickname;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
