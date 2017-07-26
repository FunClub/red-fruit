package com.taomei.dao.dtos.login;

import com.taomei.dao.entities.Invitation;

import java.util.List;

/**
 * 登录时的用户
 */
public class LoginDto {
    private String userId;
    private String nickname;
    /**
     * 是否有另一半
     */
    private boolean hasHalf;
    /**
     * 用户头像，主要用于邀请对象时的头像显示
     */
    private String profileImg;
    /**
     * 邀请人信息，没有另一半时才可能有值
     */
    private List<Invitation> invitations;

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public boolean isHasHalf() {
        return hasHalf;
    }

    public void setHasHalf(boolean hasHalf) {
        this.hasHalf = hasHalf;
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
}
