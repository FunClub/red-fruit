package com.taomei.web.album.controller;

import com.taomei.dao.dtos.album.*;
import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.album.Album;
import com.taomei.service.album.iservice.IAlbumService;
import com.taomei.service.share.ImageService;
import com.taomei.web.share.anotaions.SetHalfId;
import com.taomei.web.share.anotaions.SetId;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 相册模块控制器
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    private final IAlbumService albumService;
    private final ImageService imageService;
    @Autowired
    public AlbumController(@Qualifier("baseAlbumService") IAlbumService albumService, ImageService imageService) {
        this.albumService = albumService;
        this.imageService = imageService;
    }

    /**
     * 移动相片到其他相册
     * @param dto MoveAlbumPhotoDto
     * @return 统一数据对象
     */
    @PutMapping("/photo/albumId")
    public ResultView moveAlbumPhoto(@RequestBody MoveAlbumPhotoDto dto){
        return ResultViewUtil.success(albumService.moveAlbumPhoto(dto));
    }
    /**
     * 修改相册封面
     * @param dto 修改相册封面 dto
     * @return 统一数据对象
     */
    @PutMapping("/cover")
    public ResultView updateAlbumCover(@RequestBody UpdateAlbumCoverDto dto){
        return ResultViewUtil.success(albumService.updateAlbumCover(dto));
    }
    /**
     * 修改相片信息
     * @param dto 修改信息dto
     * @return 统一数据对象
     */
    @PutMapping("/photo-info")
    public ResultView updatePhotoInfo(@RequestBody UpdatePhotoInfoDto dto){
        return ResultViewUtil.success(albumService.updatePhotoInfo(dto));
    }
    /**
     * 查询一个相册及其相片
     * @param userId 用户id
     * @param albumId 相册id
     * @return 统一数据对象
     */
    @GetMapping("/{albumId}/photos")
    @SetId
    public ResultView selectPhotos(String userId,String halfId,@PathVariable("albumId") String albumId){
        SelectAlbumPhotoDto dto = new SelectAlbumPhotoDto();
        dto.setAlbumId(albumId);
        dto.setUserId(userId);
        dto.setHalfId(halfId);
        return ResultViewUtil.success(albumService.selectPhotos(dto));
    }
    /**
     * 上传相片
     * @param folder 相片文件夹
     * @param imgs 文件集合
     * @return
     * @throws IOException
     */
    @PostMapping("/{folder}/photo")
    public ResultView uploadPhoto(@PathVariable("folder") String folder, @RequestBody List<MultipartFile> imgs) throws IOException {
        return  ResultViewUtil.success(imageService.generateUpLoadPhotoDto(imgs,folder));
    }

    /**
     * 添加相片
     * @param dto 添加相片dto
     * @return
     */
    @PostMapping("/photo")
    public ResultView addPhoto(@RequestBody AddPhotoDto dto) throws Exception {
        return ResultViewUtil.success(albumService.insertPhotos(dto));
    }
    /**
     * 创建相册额
     * @param halfId 情侣id
     * @param album 相册文档
     * @return 统一数据对象
     */
    @PostMapping("/")
    @SetHalfId
    public ResultView insertAlbum(String halfId,@RequestBody Album album){
        album.setHalfId(halfId);
        return ResultViewUtil.success(albumService.insertAlbum(album));
    }

    /**
     * 查询用户相册信息
     * @param userId 用户id
     * @param halfId 情侣id
     * @return 统一数据对象
     */
    @GetMapping("/")
    @SetId
    public ResultView selectHalfAlbum(String userId,String halfId){
        IdsDto dto = new IdsDto();
        dto.setHalfId(halfId);
        dto.setUserId(userId);
        return ResultViewUtil.success(albumService.selectHalfAlbum(dto));
    }

}
