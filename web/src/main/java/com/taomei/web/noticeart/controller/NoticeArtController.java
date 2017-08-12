package com.taomei.web.noticeart.controller;

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

    @PostMapping("/")
    @SetUserId
    public ResultView selectNoticeArt(String userId, @RequestBody SelectNoticeArtConditionDto dto){
        return ResultViewUtil.success(noticeArtService.selectNoticeArt(dto));
    }
}
