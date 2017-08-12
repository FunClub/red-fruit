package com.taomei.service.noticeart.iservice;

import com.taomei.dao.dtos.noticeart.SelectNoticeArtConditionDto;
import com.taomei.dao.dtos.noticeart.ShowPagedNoticeArtDto;
import com.taomei.dao.entities.NoticeArt;

/**
 * 通知动态接口
 */
public interface INoticeArtService {

    /**
     * 查询用户动态信息
     * @param dto 通知动态查询条件dto
     * @return 分页的动态信息
     */
    ShowPagedNoticeArtDto selectNoticeArt(SelectNoticeArtConditionDto dto);
    /**
     * 插入通知动态
     * @param noticeArt 通知动态文档
     * @return 插入成功与否
     */
    boolean insertNoticeArt(NoticeArt noticeArt);
}
