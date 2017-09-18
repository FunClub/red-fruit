package com.taomei.dao.dtos.email;

import com.taomei.dao.entities.email.EmailContent;

import java.util.List;

/**
 * 显示一个邮件的dto
 */
public class ShowEmailDto {
    private String emailId;
    private String emailUserId;
    private String nickname;
    private String profileImg;
    private String sex;
    private String character;
    private String city;
    private String profession;
    private List<ShowEmailContentDto> emailContents;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailUserId() {
        return emailUserId;
    }

    public void setEmailUserId(String emailUserId) {
        this.emailUserId = emailUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<ShowEmailContentDto> getEmailContents() {
        return emailContents;
    }

    public void setEmailContents(List<ShowEmailContentDto> emailContents) {
        this.emailContents = emailContents;
    }
}
