package com.taomei.dao.dtos.album;

import com.taomei.dao.entities.album.Photo;

import java.util.List;

/**
 * 删除相片的dto
 */
public class DeletePhotoDto {
    private List<Photo> photos;
    private String albumId;

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
