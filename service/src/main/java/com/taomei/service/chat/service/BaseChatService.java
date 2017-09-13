package com.taomei.service.chat.service;

import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.chat.ShowChatDto;
import com.taomei.dao.entities.chat.Chat;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.ChatRepository;
import com.taomei.service.chat.iservice.IChatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BaseChatService implements IChatService {
    private final ChatRepository chatRepository;
    private final UserMapper userMapper;
    @Autowired
    public BaseChatService(ChatRepository chatRepository, UserMapper userMapper) {
        this.chatRepository = chatRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<ShowChatDto> selectChats(IdsDto dto) {
        String halfId = dto.getHalfId();
        String userId = dto.getUserId();
        List<Chat> chats =chatRepository.findByHalfId(halfId);
        List<ShowChatDto> showChatDtos = new ArrayList<>();
        ShowChatDto showChatDto=null;
        for (Chat chat:chats){
            showChatDto = new ShowChatDto();
            BeanUtils.copyProperties(chat,showChatDto);
            UserNPInfoDto userNPInfoDto = userMapper.selectUserNPInfo(chat.getSendUserId());
            BeanUtils.copyProperties(userNPInfoDto,showChatDto);
            showChatDtos.add(showChatDto);
        }
        return showChatDtos;
    }
}
