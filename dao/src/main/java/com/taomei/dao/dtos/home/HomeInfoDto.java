package com.taomei.dao.dtos.home;

import com.taomei.dao.entities.Users;

/**
 * home模块的用户基本资料
 */
public class HomeInfoDto {
    private String userId;
    private String halfId;
    private String halfUserId;
    private String nickname;
    private String halfNickname;
    private String halfProfileImg;
    private String profileImg;

    public String getHalfUserId() {
        return halfUserId;
    }

    public void setHalfUserId(String halfUserId) {
        this.halfUserId = halfUserId;
    }

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

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }

    public String getHalfNickname() {
        return halfNickname;
    }

    public void setHalfNickname(String halfNickname) {
        this.halfNickname = halfNickname;
    }

    public String getHalfProfileImg() {
        return halfProfileImg;
    }

    public void setHalfProfileImg(String halfProfileImg) {
        this.halfProfileImg = halfProfileImg;
    }
}
