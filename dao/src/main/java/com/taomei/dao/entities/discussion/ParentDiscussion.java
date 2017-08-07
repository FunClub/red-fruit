package com.taomei.dao.entities.discussion;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 评论文档
 */
@Document(collection = "discussion")
public class ParentDiscussion extends SubDiscussion{
    @Id
    private String discussionId;
    /**
     * 动态Id,比如心情等
     */
    private String artId;
    /**
     * 子评论长度用于最热评论排序显示
     */
    private Long subDiscussionsLength;
    private List<SubDiscussion> subDiscussions;
    private List<String> thumbsUpUserIds;

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public Long getSubDiscussionsLength() {
        return subDiscussionsLength;
    }

    public void setSubDiscussionsLength(Long subDiscussionsLength) {
        this.subDiscussionsLength = subDiscussionsLength;
    }

    public List<SubDiscussion> getSubDiscussions() {
        return subDiscussions;
    }

    public void setSubDiscussions(List<SubDiscussion> subDiscussions) {
        this.subDiscussions = subDiscussions;
    }

    public List<String> getThumbsUpUserIds() {
        return thumbsUpUserIds;
    }

    public void setThumbsUpUserIds(List<String> thumbsUpUserIds) {
        this.thumbsUpUserIds = thumbsUpUserIds;
    }
}
