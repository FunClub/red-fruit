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
    private List<SubDiscussion> subDiscussions;
    public List<SubDiscussion> getSubDiscussions() {
        return subDiscussions;
    }
    public void setSubDiscussions(List<SubDiscussion> subDiscussions) {
        this.subDiscussions = subDiscussions;
    }
}
