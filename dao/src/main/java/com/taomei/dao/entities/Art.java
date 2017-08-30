package com.taomei.dao.entities;
import java.util.List;

/**
 * 基本动态
 */
public class Art {
    /**
     * 权限
     */
    private Integer limit;
    private List<String> thumbsUpUserIds;

    /**
     * 动态所属用户id
     */
    private String userId;

    /**
     * 情侣id
     */
    private String halfId;
    /**
     * 原创用户id
     */
    private  String originalUserId;

    /**
     * 原创动态id
     */
    private String originalArtId;
    private Boolean original;
    private String date;
    public Integer getLimit() {
        return limit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<String> getThumbsUpUserIds() {
        return thumbsUpUserIds;
    }

    public void setThumbsUpUserIds(List<String> thumbsUpUserIds) {
        this.thumbsUpUserIds = thumbsUpUserIds;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }
}
