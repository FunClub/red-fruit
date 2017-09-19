package com.taomei.dao.dtos.email;

import com.taomei.dao.entities.email.EmailContent;

/**
 * 插入邮件内容dto
 */
public class InsertEmailContentDto {
    private String emailId;
    private EmailContent emailContent;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public EmailContent getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(EmailContent emailContent) {
        this.emailContent = emailContent;
    }
}
