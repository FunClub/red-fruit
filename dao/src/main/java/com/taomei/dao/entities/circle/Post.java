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
    private String circleId;
    private String userId;
    private String title;
    private String content;
    private Boolean isAnonymous;
    private List<String> imgs;
    private String date;
    private List<String> visitUserIds;
    /**
     * 更新时间,评论回复等
     */
    private String updateDate;
    private List<String> thumbsUpUserIds;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<String> getThumbsUpUserIds() {
        return thumbsUpUserIds;
    }

    public void setThumbsUpUserIds(List<String> thumbsUpUserIds) {
        this.thumbsUpUserIds = thumbsUpUserIds;
    }
}
