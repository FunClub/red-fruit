package com.taomei.dao.dtos.discussion;

import java.util.List;

public class ShowParentDiscussionDto extends ShowSubDiscussionDto{
    private String discussionId;
    private List<ShowSubDiscussionDto> subDiscussionDtos;

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
