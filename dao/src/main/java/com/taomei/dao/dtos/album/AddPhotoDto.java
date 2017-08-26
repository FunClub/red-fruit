package com.taomei.dao.dtos.album;

import com.taomei.dao.entities.album.Photo;

import java.util.List;

/**
 * 添加相片的dto
 */
public class AddPhotoDto {
    private List<Photo> photos;
    private String albumId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
