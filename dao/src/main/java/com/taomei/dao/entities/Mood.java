package com.taomei.dao.entities;

import com.taomei.dao.entities.discussion.ParentDiscussion;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 心情文档
 */
@Document(collection = "mood")
public class Mood {
    @Id
    private String moodId;
    private String userId;
    private String halfId;
    private String content;
    private Boolean original;
    private String date;
    private List<String> imgs;
    private Integer limit;
    private List<String> thumbsUpUserIds;
    /**
     * 原创用户id
     */
    private  String originalUserId;

    /**
     * 原创动态id
     */
    private  String originalArtId;

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getLimit() {
        return limit;
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

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
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
}
