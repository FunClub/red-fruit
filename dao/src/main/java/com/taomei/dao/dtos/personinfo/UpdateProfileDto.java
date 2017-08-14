package com.taomei.dao.dtos.personinfo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 更新用户头像
 */
public class UpdateProfileDto {
    private String userId;
    /**
     * 缩略头像
     */
    private MultipartFile profileImgFile;

    private String profileImg;
    /**
     * 原始头像
     */
    private String originalProfileImg;
    private String oldProfileImg;
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

    public String getOldProfileImg() {
        return oldProfileImg;
    }

    public void setOldProfileImg(String oldProfileImg) {
        this.oldProfileImg = oldProfileImg;
    }

    public String getOriginalProfileImg() {
        return originalProfileImg;
    }

    public void setOriginalProfileImg(String originalProfileImg) {
        this.originalProfileImg = originalProfileImg;
    }

    public MultipartFile getProfileImgFile() {
        return profileImgFile;
    }

    public void setProfileImgFile(MultipartFile profileImgFile) {
        this.profileImgFile = profileImgFile;
    }
}
