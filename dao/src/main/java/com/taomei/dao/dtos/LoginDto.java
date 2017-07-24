package com.taomei.dao.dtos;

import java.math.BigInteger;

/**
 * 登录时的用户
 */
public class LoginDto {
    private BigInteger userId;
    private String nickname;
    /**
     * 是否有另一半
     */
    private boolean hasHalf;
    /**
     * 用户头像，主要用于邀请对象时的头像显示
     */
    private String profileImg;

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

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
