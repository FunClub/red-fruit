package com.taomei.dao.dtos.album;

/**
 * 显示一个相片的dto
 */
public class ShowPhotoDto {
    private String photoId;
    private String userId;
    private String name;
    private String path;
    private Long thumbsUpCount;
    private Long discussionCount;
    private Boolean thumbsUpAble;
    private String description;

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
