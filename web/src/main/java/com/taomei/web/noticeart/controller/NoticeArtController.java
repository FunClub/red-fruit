package com.taomei.web.noticeart.controller;

import com.taomei.dao.dtos.noticeart.DeleteNoticeArtDto;
import com.taomei.dao.dtos.noticeart.SelectNoticeArtConditionDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.noticeart.iservice.INoticeArtService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notice-art")
public class NoticeArtController {
    private final INoticeArtService noticeArtService;

    @Autowired
    public NoticeArtController(@Qualifier("baseNoticeArtService") INoticeArtService noticeArtService) {
        this.noticeArtService = noticeArtService;
    }

    /**
     * 查询动态通知
     * @param userId 用户id
     * @param dto 查询DTO
     * @return 数据统一对象
     */
    @PostMapping("/")
    @SetUserId
    public ResultView selectNoticeArt(String userId, @RequestBody SelectNoticeArtConditionDto dto){
        dto.setUserId(userId);
        return ResultViewUtil.success(noticeArtService.selectNoticeArt(dto));
    }

    /**
     * 删除动态通知
     * @param userId 用户id
     * @param dto 删除DTO
     * @return 数据统一对象
     */
    @PatchMapping("/")
    @SetUserId
    public ResultView deleteNoticeArt(String userId, @RequestBody DeleteNoticeArtDto dto){
        dto.setNoticeArtUserId(userId);
        return ResultViewUtil.success(noticeArtService.deleteNoticeArt(dto));
    }
}
