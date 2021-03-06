package com.taomei.dao.dtos.noticeart;

import com.taomei.dao.dtos.discussion.ShowParentDiscussionDto;
import com.taomei.dao.entities.discussion.ParentDiscussion;

/**
 * 显示单个动态的dto
 */
public class ShowNoticeArtDto {
    private String generateUserId;
    private String generateNickname;
    private String generateProfileImg;
    private String artId;
    private String noticeArtId;
    private String artUserId;
    private String originalArtId;
    private String originalUserId;
    private Boolean original;
    private String artType;
    private String noticeArtType;
    private String sortDate;
    private String shortDate;
    private String artNickname;
    private String firstArtImg;
    private String artContent;
    private String currentContent;
    private ShowParentDiscussionDto discussion;

    public String getNoticeArtId() {
        return noticeArtId;
    }

    public void setNoticeArtId(String noticeArtId) {
        this.noticeArtId = noticeArtId;
    }

    public String getGenerateNickname() {
        return generateNickname;
    }

    public void setGenerateNickname(String generateNickname) {
        this.generateNickname = generateNickname;
    }

    public String getGenerateUserId() {
        return generateUserId;
    }

    public void setGenerateUserId(String generateUserId) {
        this.generateUserId = generateUserId;
    }

    public String getGenerateProfileImg() {
        return generateProfileImg;
    }

    public void setGenerateProfileImg(String generateProfileImg) {
        this.generateProfileImg = generateProfileImg;
    }

    public String getArtUserId() {
        return artUserId;
    }

    public void setArtUserId(String artUserId) {
        this.artUserId = artUserId;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getNoticeArtType() {
        return noticeArtType;
    }

    public void setNoticeArtType(String noticeArtType) {
        this.noticeArtType = noticeArtType;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public String getArtNickname() {
        return artNickname;
    }

    public void setArtNickname(String artNickname) {
        this.artNickname = artNickname;
    }

    public String getFirstArtImg() {
        return firstArtImg;
    }

    public void setFirstArtImg(String firstArtImg) {
        this.firstArtImg = firstArtImg;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
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

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public ShowParentDiscussionDto getDiscussion() {
        return discussion;
    }

    public void setDiscussion(ShowParentDiscussionDto discussion) {
        this.discussion = discussion;
    }

    public String getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(String currentContent) {
        this.currentContent = currentContent;
    }
}
