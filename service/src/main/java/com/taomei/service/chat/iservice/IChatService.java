package com.taomei.service.chat.iservice;

import com.taomei.dao.dtos.share.IdsDto;
import com.taomei.dao.dtos.chat.ShowChatDto;
import com.taomei.dao.entities.chat.Chat;

import java.util.List;

public interface IChatService {
    /**
     * 查询聊天记录
     * @param dto id dto
     * @return
     */
    public List<ShowChatDto> selectChats(IdsDto dto);

    /**
     *
     * 插入聊天记录
     * @param chat
     * @return
     */
    Chat insertChat(Chat chat);
}
