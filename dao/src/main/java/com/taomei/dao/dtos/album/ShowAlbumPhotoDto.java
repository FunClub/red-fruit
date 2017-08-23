package com.taomei.dao.dtos.album;

import java.util.List;

/**
 * 显示一个相册的信息及其相片
 */
public class ShowAlbumPhotoDto {
    /**
     * 当前正在查询的相册
     */
    private  ShowHalfAlbumDto currentAlbum;
    /**
     * 相册集合，用于显示上传相片时的相册信息
     */
    private List<ShowHalfAlbumDto> albums;
    private List<ShowPhotoDto> photos;

    public ShowHalfAlbumDto getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(ShowHalfAlbumDto currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public List<ShowPhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ShowPhotoDto> photos) {
        this.photos = photos;
    }

    public List<ShowHalfAlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(List<ShowHalfAlbumDto> albums) {
        this.albums = albums;
    }
}
