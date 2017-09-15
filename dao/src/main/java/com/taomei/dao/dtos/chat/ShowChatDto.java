package com.taomei.dao.dtos.chat;

import java.io.Serializable;

/**
 * 显示消息DTO
 */
public class ShowChatDto implements Serializable {
    private String sendUserId;
    private String receivedUserId;
    private String nickname;
    private String sendProfileImg;
    private String content;
    private String date;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
