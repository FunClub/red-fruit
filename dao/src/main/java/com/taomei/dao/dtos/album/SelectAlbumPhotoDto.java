package com.taomei.dao.dtos.album;

/**
 * 查询相册及其相片的条件dto
 */
public class SelectAlbumPhotoDto {
    private String userId;
    private String albumId;
    private String halfId;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }
}
