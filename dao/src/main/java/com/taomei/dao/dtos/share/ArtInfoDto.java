package com.taomei.dao.dtos.share;

/**
 * 动态信息dto
 */
public class ArtInfoDto {
    private String nickname;
    private String profile;
    private Long discussionCount;
    private Long thumbsUpCount;
    private Boolean thumbsUpAble;
    private String howLongAgo;

    public String getHowLongAgo() {
        return howLongAgo;
    }

    public void setHowLongAgo(String howLongAgo) {
        this.howLongAgo = howLongAgo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
