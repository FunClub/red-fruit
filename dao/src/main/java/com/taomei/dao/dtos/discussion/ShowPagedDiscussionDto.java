package com.taomei.dao.dtos.discussion;

import com.taomei.dao.dtos.mood.ShowMoodDto;

import java.util.List;

public class ShowPagedDiscussionDto {
    private long totalElements;
    private List<ShowParentDiscussionDto> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<ShowParentDiscussionDto> getContent() {
        return content;
    }

    public void setContent(List<ShowParentDiscussionDto> content) {
        this.content = content;
    }
}
