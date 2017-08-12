package com.taomei.dao.entities;

import com.taomei.dao.entities.discussion.ParentDiscussion;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 我的动态文档
 */
@Document(collection = "noticeArt")
public class NoticeArt {
    private String noticeArtId;
    private String date;
    private String artId;
    private String originalArtId;
    private String originalUserId;
    private Boolean original;
    /**
     * 通知动态所属用户的id
     */
    private String noticeArtUserId;

    /**
     * 生成此动态的用户id
     */
    private String generateUserId;

    /**
     * 通知类型，点赞，评论，回复等
     */
    private String noticeArtType;

    private String artType;
    /**
     * 通知是否已读
     */
    private boolean state;
    private String artContent;
    private String firstArtImg;
    private ParentDiscussion discussion;

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public String getNoticeArtId() {
        return noticeArtId;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    public String getFirstArtImg() {
        return firstArtImg;
    }

    public void setFirstArtImg(String firstArtImg) {
        this.firstArtImg = firstArtImg;
    }

    public void setNoticeArtId(String noticeArtId) {
        this.noticeArtId = noticeArtId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoticeArtUserId() {
        return noticeArtUserId;
    }

    public void setNoticeArtUserId(String noticeArtUserId) {
        this.noticeArtUserId = noticeArtUserId;
    }

    public String getGenerateUserId() {
        return generateUserId;
    }

    public void setGenerateUserId(String generateUserId) {
        this.generateUserId = generateUserId;
    }



    public String getNoticeArtType() {
        return noticeArtType;
    }

    public void setNoticeArtType(String noticeArtType) {
        this.noticeArtType = noticeArtType;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }



    public ParentDiscussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(ParentDiscussion discussion) {
        this.discussion = discussion;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getOriginalArtId() {
        return originalArtId;
    }

    public void setOriginalArtId(String originalArtId) {
        this.originalArtId = originalArtId;
    }

    public String getOriginalUserId() {
        return originalUserId;
    }

    public void setOriginalUserId(String originalUserId) {
        this.originalUserId = originalUserId;
    }
}
