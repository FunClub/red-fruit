package com.taomei.dao.dtos.album;

/**
 * 显示一个相片的dto
 */
public class ShowPhotoDto {
    private String photoId;
    private String userId;
    private String nickname;
    private String profile;
    private String uploadDate;
    private String name;
    private String path;
    private Long thumbsUpCount;
    private Long discussionCount;
    private Boolean thumbsUpAble;
    private String description;
    private String waterMark;
    private Integer bright;
    private Integer contrast;
    private Integer sharpen;
    private Integer blurR;
    private Integer blurS;
    private Integer zoomSize;
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWaterMark() {
        return waterMark;
    }

    public void setWaterMark(String waterMark) {
        this.waterMark = waterMark;
    }

    public Integer getBright() {
        return bright;
    }

    public void setBright(Integer bright) {
        this.bright = bright;
    }

    public Integer getContrast() {
        return contrast;
    }

    public void setContrast(Integer contrast) {
        this.contrast = contrast;
    }

    public Integer getSharpen() {
        return sharpen;
    }

    public void setSharpen(Integer sharpen) {
        this.sharpen = sharpen;
    }

    public Integer getBlurR() {
        return blurR;
    }

    public void setBlurR(Integer blurR) {
        this.blurR = blurR;
    }

    public Integer getBlurS() {
        return blurS;
    }

    public void setBlurS(Integer blurS) {
        this.blurS = blurS;
    }

    public Integer getZoomSize() {
        return zoomSize;
    }

    public void setZoomSize(Integer zoomSize) {
        this.zoomSize = zoomSize;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoId() {
        return photoId;
    }

    public Boolean getThumbsUpAble() {
        return thumbsUpAble;
    }

    public void setThumbsUpAble(Boolean thumbsUpAble) {
        this.thumbsUpAble = thumbsUpAble;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getThumbsUpCount() {
        return thumbsUpCount;
    }

    public void setThumbsUpCount(Long thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }

    public Long getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(Long discussionCount) {
        this.discussionCount = discussionCount;
    }
}
