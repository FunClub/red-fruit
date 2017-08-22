package com.taomei.dao.dtos.album;

import java.util.List;

/**
 * 显示一个相册的信息及其相片
 */
public class ShowAlbumPhotoDto {
    private ShowHalfAlbumDto album;
    private List<ShowPhotoDto> photos;

    public ShowHalfAlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(ShowHalfAlbumDto album) {
        this.album = album;
    }

    public List<ShowPhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ShowPhotoDto> photos) {
        this.photos = photos;
    }
}
