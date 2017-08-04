package com.taomei.dao.dtos.home;

import com.taomei.dao.entities.Users;

/**
 * home模块的用户基本资料
 */
public class HomeInfoDto {
    private String userId;
    private String nickname;
    private String profileImg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
