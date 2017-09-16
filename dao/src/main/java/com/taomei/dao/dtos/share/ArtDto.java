package com.taomei.dao.dtos.share;

/**
 * 动态dto的通用属性
 */
public class ArtDto {
    private String userId;
    /**
     * 原创用户id
     */
    private String originalUserId;

    /**
     * 原创动态id
     */
    private String originalArtId;
    private Boolean original;
    private Long discussionCount;
    private Long thumbsUpCount;
    private Boolean thumbsUpAble;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOriginalUserId() {
        return originalUserId;
    }

    public void setOriginalUserId(String originalUserId) {
        this.originalUserId = originalUserId;
    }

    public String getOriginalArtId() {
        return originalArtId;
    }

    public void setOriginalArtId(String originalArtId) {
        this.originalArtId = originalArtId;
    }

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public Long getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(Long discussionCount) {
        this.discussionCount = discussionCount;
    }

    public Long getThumbsUpCount() {
        return thumbsUpCount;
    }

    public void setThumbsUpCount(Long thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }

    public Boolean getThumbsUpAble() {
        return thumbsUpAble;
    }

    public void setThumbsUpAble(Boolean thumbsUpAble) {
        this.thumbsUpAble = thumbsUpAble;
    }
}
