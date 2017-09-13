package com.taomei.service.chat.iservice;

import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.dtos.chat.ShowChatDto;

import java.util.List;

public interface IChatService {
    /**
     * 查询聊天记录
     * @param dto id dto
     * @return
     */
    public List<ShowChatDto> selectChats(IdsDto dto);
}
