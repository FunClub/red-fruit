package com.taomei.dao.entities.email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 邮件文档
 */
@Document(collection = "email")
public class Email {
    @Id
    private String emailId;
    private String fromUserId;
    private String receiveUserId;
    private String date;
    private Boolean fromState;
    private Boolean receiveState;
    private List<EmailContent> emailContents;

    public Boolean getFromState() {
        return fromState;
    }

    public void setFromState(Boolean fromState) {
        this.fromState = fromState;
    }

    public Boolean getReceiveState() {
        return receiveState;
    }

    public void setReceiveState(Boolean receiveState) {
        this.receiveState = receiveState;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EmailContent> getEmailContents() {
        return emailContents;
    }

    public void setEmailContents(List<EmailContent> emailContents) {
        this.emailContents = emailContents;
    }
}
