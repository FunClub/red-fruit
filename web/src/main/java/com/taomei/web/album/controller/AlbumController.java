package com.taomei.web.album.controller;

import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.album.Album;
import com.taomei.service.album.iservice.IAlbumService;
import com.taomei.web.share.anotaions.SetHalfId;
import com.taomei.web.share.anotaions.SetId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * 相册模块控制器
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    private final IAlbumService albumService;

    @Autowired
    public AlbumController(@Qualifier("baseAlbumService") IAlbumService albumService) {
        this.albumService = albumService;
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
