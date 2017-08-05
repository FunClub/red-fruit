package com.taomei.dao.dtos.mood;

import com.taomei.dao.entities.Mood;

import java.util.List;

/**
 * 分页的心情dto
 */
public class ShowPagedMoodDto {
    private long totalElements;
    private List<ShowMoodDto> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }


    public List<ShowMoodDto> getContent() {
        return content;
    }

    public void setContent(List<ShowMoodDto> content) {
        this.content = content;
    }

}
