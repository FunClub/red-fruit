package com.taomei.dao.dtos.album;

import com.taomei.dao.entities.album.Album;

import java.util.List;

/**
 * 显示所有相册信息的dto
 */
public class ShowAllAlbumDto {
    private Long totalPhoto;
    private List<ShowHalfAlbumDto> albums;

    public Long getTotalPhoto() {
        return totalPhoto;
    }

    public void setTotalPhoto(Long totalPhoto) {
        this.totalPhoto = totalPhoto;
    }

    public List<ShowHalfAlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(List<ShowHalfAlbumDto> albums) {
        this.albums = albums;
    }
}
