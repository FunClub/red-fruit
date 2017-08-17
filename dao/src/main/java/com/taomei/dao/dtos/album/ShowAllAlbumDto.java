package com.taomei.dao.dtos.album;

import com.taomei.dao.entities.album.Album;

import java.util.List;

/**
 * 显示所有相册信息的dto
 */
public class ShowAllAlbumDto {
    private Long totalPhoto;
    private Long totalThumbsUp;
    private List<Album> albums;

    public Long getTotalPhoto() {
        return totalPhoto;
    }

    public void setTotalPhoto(Long totalPhoto) {
        this.totalPhoto = totalPhoto;
    }

    public Long getTotalThumbsUp() {
        return totalThumbsUp;
    }

    public void setTotalThumbsUp(Long totalThumbsUp) {
        this.totalThumbsUp = totalThumbsUp;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
