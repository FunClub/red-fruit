package com.taomei.dao.dtos.mood;

import com.taomei.dao.entities.Mood;

/**
 * 用于显示单个心情的dto
 */
public class ShowMoodDto{
    private Mood mood;
    private String moodId;
    private String nickname;
    private String profileImg;

    /**
     * 多久之前发的
     */
    private String howLongAgo;

    /**
     * 是否显示分类时间
     */
    private boolean showSortDate;
    /**
     * 分类时间
     */
    private String sortDate;

    /**
     * 简短时间
     */
    private String shortDate;

    private Long discussionCount;
    private Long thumbsUpCount;
    private Boolean thumbsUpAble;

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

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public boolean isShowSortDate() {
        return showSortDate;
    }

    public void setShowSortDate(boolean showSortDate) {
        this.showSortDate = showSortDate;
    }

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getHowLongAgo() {
        return howLongAgo;
    }

    public void setHowLongAgo(String howLongAgo) {
        this.howLongAgo = howLongAgo;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }


}
