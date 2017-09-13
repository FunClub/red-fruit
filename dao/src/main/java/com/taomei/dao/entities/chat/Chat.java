package com.taomei.dao.entities.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
public class Chat {
    @Id
    private String chatId;
    private String halfId;
    private String sendUserId;
    private String receivedUserId;
    private String content;
    private String date;

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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
