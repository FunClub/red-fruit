package com.taomei.dao.entities.album;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "photo")
public class Photo {
    @Id
    private String photoId;
    private String albumId;
    private String path;
    private String name;
    private String waterMark;
    private Integer bright;
    private Integer contrast;
    private Integer sharpen;
    private Integer blurR;
    private Integer blurS;
    private List<String> thumbsUpUserId;
    private Long discussionCount;

    public Long getDiscussionCount() {
        return discussionCount;
    }

    public void setDiscussionCount(Long discussionCount) {
        this.discussionCount = discussionCount;
    }

    public List<String> getThumbsUpUserId() {
        return thumbsUpUserId;
    }

    public void setThumbsUpUserId(List<String> thumbsUpUserId) {
        this.thumbsUpUserId = thumbsUpUserId;
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
