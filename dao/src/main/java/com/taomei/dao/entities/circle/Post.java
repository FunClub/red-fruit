package com.taomei.dao.entities.circle;

import com.taomei.dao.entities.Art;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 帖子文档
 */
@Document(collection = "post")
public class Post{
    @Id
    private String postId;
    private String circleName;
    private String userId;
    private String title;
    private String content;
    private Boolean anonymous;
    private List<String> imgs;
    private String date;
    private List<String> visitUserIds;
    private Long discussionCount;
    private List<String> thumbsUpUserIds;
    private Boolean original;

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(Long discussionCount) {
        this.discussionCount = discussionCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getVisitUserIds() {
        return visitUserIds;
    }

    public void setVisitUserIds(List<String> visitUserIds) {
        this.visitUserIds = visitUserIds;
    }


    public List<String> getThumbsUpUserIds() {
        return thumbsUpUserIds;
    }

    public void setThumbsUpUserIds(List<String> thumbsUpUserIds) {
        this.thumbsUpUserIds = thumbsUpUserIds;
    }
}
