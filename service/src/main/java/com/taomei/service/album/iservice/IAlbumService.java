package com.taomei.service.album.iservice;

import com.taomei.dao.dtos.album.*;
import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.album.Album;

/**
 * 相册接口服务
 */
public interface IAlbumService {

    /**
     * 查询一个相册及其相片
     * @param dto 查询条件dto
     * @return 显示一个相册及其相片的dto
     */
    ShowAlbumPhotoDto selectPhotos(SelectAlbumPhotoDto dto);
    /**
     * 添加相片
     * @param dto 添加相片dto
     * @return
     */
    boolean insertPhotos(AddPhotoDto dto);
    /**
     * 插入相册
     * @param album 相册文档
     * @return 参加的相册文档
     */
    Album insertAlbum(Album album);

    /**
     *查询情侣相册
     * @param dto 查询条件
     * @return 包含全部信息的dto
     */
    ShowAllAlbumDto selectHalfAlbum(SelectHalfAlbumDto dto);

    /**
     *查询情侣相册
     * @param dto 用户id和情侣id
     * @return 包含全部信息的dto
     */
    public ShowAllAlbumDto selectHalfAlbum(IdsDto dto);
}
