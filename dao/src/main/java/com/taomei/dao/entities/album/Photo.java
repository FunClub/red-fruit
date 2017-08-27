package com.taomei.dao.entities.album;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "photo")
public class Photo {
    @Id
    private String photoId;
    private String albumId;
    private String userId;
    private String path;
    private String name;
    private String waterMark;
    private Integer bright;
    private Integer contrast;
    private Integer sharpen;
    private Integer blurR;
    private Integer blurS;
    private Integer zoomSize;
    private List<String> thumbsUpUserIds;
    private Long discussionCount;
    private String uploadDate;
    private String description;

    public Integer getZoomSize() {
        return zoomSize;
    }

    public void setZoomSize(Integer zoomSize) {
        this.zoomSize = zoomSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(Long discussionCount) {
        this.discussionCount = discussionCount;
    }

    public List<String> getThumbsUpUserIds() {
        return thumbsUpUserIds;
    }

    public void setThumbsUpUserIds(List<String> thumbsUpUserIds) {
        this.thumbsUpUserIds = thumbsUpUserIds;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWaterMark() {
        return waterMark;
    }

    public void setWaterMark(String waterMark) {
        this.waterMark = waterMark;
    }

    public Integer getBright() {
        return bright;
    }

    public void setBright(Integer bright) {
        this.bright = bright;
    }

    public Integer getContrast() {
        return contrast;
    }

    public void setContrast(Integer contrast) {
        this.contrast = contrast;
    }

    public Integer getSharpen() {
        return sharpen;
    }

    public void setSharpen(Integer sharpen) {
        this.sharpen = sharpen;
    }

    public Integer getBlurR() {
        return blurR;
    }

    public void setBlurR(Integer blurR) {
        this.blurR = blurR;
    }

    public Integer getBlurS() {
        return blurS;
    }

    public void setBlurS(Integer blurS) {
        this.blurS = blurS;
    }
}
