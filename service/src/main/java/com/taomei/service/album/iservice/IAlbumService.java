package com.taomei.service.album.iservice;

import com.taomei.dao.dtos.album.*;
import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.album.Album;
import com.taomei.dao.entities.album.Photo;

import java.util.List;

/**
 * 相册接口服务
 */
public interface IAlbumService {
    /**
     * 删除相片
     * @param photos 删除的相片
     * @return 成功与否
     */
    boolean deletePhotos(List<Photo> photos);
    /**
     * 更新水印
     * @param photos 相片列表
     * @return
     */
    boolean updateWaterMark(List<Photo> photos);
    /**
     * 点赞
     * @param noticeArt 点赞dto
     * @return
     */
    boolean thumbsUp(NoticeArt noticeArt);
    /**
     * 移动相片到其他相册
     * @param dto MoveAlbumPhotoDto
     * @return 成功与否
     */
    boolean moveAlbumPhoto(MoveAlbumPhotoDto dto);
    /**
     * 更新相册封面
     * @param dto 更新相册封面dto
     * @return 成功与否
     */
    boolean updateAlbumCover(UpdateAlbumCoverDto dto);
    /**
     * 更新相册信息
     * @param dto 相册信息DTO
     * @return 成功与否
     */
    boolean updatePhotoInfo(UpdatePhotoInfoDto dto);

    /**
     * 查询一个相册及其相片
     * @param dto 查询条件dto
     * @return 显示一个相册及其相片的dto
     */
    ShowAlbumPhotoDto selectPhotos(SelectAlbumPhotoDto dto);
    /**
     * 添加相片
     * @param dto 添加相片dto
     * @return 展示相片信息的dto
     */
    List<ShowPhotoDto> insertPhotos(AddPhotoDto dto) throws Exception;
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
