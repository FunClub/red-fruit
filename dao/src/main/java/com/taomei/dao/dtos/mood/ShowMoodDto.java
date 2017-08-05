package com.taomei.dao.dtos.mood;

import com.taomei.dao.entities.Mood;

import java.util.List;

/**
 * 用于显示单个心情的dto
 */
public class ShowMoodDto {
    private Mood mood;
    private String nickname;
    private String profileImg;
    private String howLongAgo;
    private boolean showSortDate;
    private String sortDate;
    private String shortDate;

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
