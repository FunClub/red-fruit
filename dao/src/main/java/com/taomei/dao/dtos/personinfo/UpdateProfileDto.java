package com.taomei.dao.dtos.personinfo;

/**
 * 更新用户头像
 */
public class UpdateProfileDto {
    private String userId;
    /**
     * 缩略头像
     */
    private String profileImg;

    /**
     * 原始头像
     */

    private String originalProfileImg;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getOriginalProfileImg() {
        return originalProfileImg;
    }

    public void setOriginalProfileImg(String originalProfileImg) {
        this.originalProfileImg = originalProfileImg;
    }
}
