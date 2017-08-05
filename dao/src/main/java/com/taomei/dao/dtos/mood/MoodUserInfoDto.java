package com.taomei.dao.dtos.mood;

/**
 * 在心情中显示用户头像昵称等基本信息
 */
public class MoodUserInfoDto {
    private String nickname;
    private String profileImg;

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
}
