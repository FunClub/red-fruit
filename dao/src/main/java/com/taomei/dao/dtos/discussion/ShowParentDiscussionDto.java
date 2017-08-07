package com.taomei.dao.dtos.discussion;

import java.util.List;

public class ShowParentDiscussionDto extends ShowSubDiscussionDto{
    private String discussionId;
    private long thumbsUpCount;
    private boolean thumbsUpAble;
    private String profileImg;
    private List<ShowSubDiscussionDto> subDiscussionDtos;

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public boolean isThumbsUpAble() {
        return thumbsUpAble;
    }

    public void setThumbsUpAble(boolean thumbsUpAble) {
        this.thumbsUpAble = thumbsUpAble;
    }

    public long getThumbsUpCount() {
        return thumbsUpCount;
    }

    public void setThumbsUpCount(long thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }



    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public List<ShowSubDiscussionDto> getSubDiscussionDtos() {
        return subDiscussionDtos;
    }

    public void setSubDiscussionDtos(List<ShowSubDiscussionDto> subDiscussionDtos) {
        this.subDiscussionDtos = subDiscussionDtos;
    }
}
