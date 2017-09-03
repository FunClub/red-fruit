package com.taomei.dao.dtos.base;

/**
 * 动态信息dto
 */
public class ArtInfoDto {
    private String nickname;
    private String profileImg;
    private Long discussionCount;
    private Long thumbsUpCount;
    private Boolean thumbsUpAble;

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
