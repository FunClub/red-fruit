package com.taomei.dao.dtos.base;

import java.util.List;

/**
 * 分页结果dto
 * @param <T>动态dto
 */
public class ShowPagedArtDto<T> {
    private Long totalElements;
    private List<T> content;

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
