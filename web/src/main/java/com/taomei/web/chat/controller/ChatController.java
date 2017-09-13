package com.taomei.web.chat.controller;

import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.chat.iservice.IChatService;
import com.taomei.web.share.anotaions.SetId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final IChatService chatService;

    @Autowired
    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    @SetId
    public ResultView selectChat(String userId,String halfId){
        IdsDto dto = new IdsDto();
        dto.setUserId(userId);
        dto.setHalfId(halfId);
        return ResultViewUtil.success(chatService.selectChats(dto));
    }
}
