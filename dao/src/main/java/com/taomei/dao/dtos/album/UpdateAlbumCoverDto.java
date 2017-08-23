package com.taomei.dao.dtos.album;

/**
 * 更新相册封面dto
 */
public class UpdateAlbumCoverDto {
    private String albumId;
    private String coverImg;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
