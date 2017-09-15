package com.taomei.web.share.websocket.message;

/**
 * 通知消息
 */
public class NoticeMessage {
    private String sendUserId;
    private String receivedUserId;
    private String sendNickname;
    private String sendProfileImg;
    private String content;
    private String date;
    private String halfId;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public String getSendNickname() {
        return sendNickname;
    }

    public void setSendNickname(String sendNickname) {
        this.sendNickname = sendNickname;
    }

    public String getSendProfileImg() {
        return sendProfileImg;
    }

    public void setSendProfileImg(String sendProfileImg) {
        this.sendProfileImg = sendProfileImg;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
