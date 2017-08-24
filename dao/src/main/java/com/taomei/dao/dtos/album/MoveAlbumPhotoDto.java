package com.taomei.dao.dtos.album;

import java.util.List;

/**
 * 移动相片到其他相册的dto
 */
public class MoveAlbumPhotoDto {
    /**
     * 目标相册id
     */
    private String tarGetAlbumId;
    /**
     * 被移动的相片的相册id
     */
    private String movedAlbumId;
    private List<String> photoIds;

    public String getTarGetAlbumId() {
        return tarGetAlbumId;
    }

    public void setTarGetAlbumId(String tarGetAlbumId) {
        this.tarGetAlbumId = tarGetAlbumId;
    }

    public String getMovedAlbumId() {
        return movedAlbumId;
    }

    public void setMovedAlbumId(String movedAlbumId) {
        this.movedAlbumId = movedAlbumId;
    }

    public List<String> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(List<String> photoIds) {
        this.photoIds = photoIds;
    }
}
