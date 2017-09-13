package com.taomei.web.share.websocket.serverendpoint;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.Half;
import com.taomei.dao.entities.chat.Chat;
import com.taomei.dao.repository.ChatRepository;
import com.taomei.service.share.utils.TimeUtil;
import com.taomei.web.share.configuration.CustomSpringConfigurator;
import com.taomei.web.share.websocket.decode.NoticeMessageEncoder;
import com.taomei.web.share.websocket.encode.NoticeMessageDecoder;
import com.taomei.web.share.websocket.message.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理聊天信息的socket
 */
@ServerEndpoint(value="/chat/{userId}",configurator = CustomSpringConfigurator.class,
        encoders = {NoticeMessageEncoder.class},
        decoders = {NoticeMessageDecoder.class}
)
@Component
public class ChatEndPoint {
    /**
     * 保存会话
     */
    private Map<String,Session> chatSessionMap;
    private final NoticeEndPoint noticeEndPoint;
    private final ChatRepository chatRepository;
    private final  static Logger LOGGER = LoggerFactory.getLogger(ChatEndPoint.class);
    private final HttpSession session;
    @Autowired
    public ChatEndPoint( NoticeEndPoint noticeEndPoint, ChatRepository chatRepository, HttpSession session) {
        this.chatSessionMap = new HashMap<>();
        this.noticeEndPoint = noticeEndPoint;
        this.chatRepository = chatRepository;
        this.session = session;
    }

    @OnOpen
    public void open(Session session, @PathParam("userId")String userId) throws IOException {
        this.chatSessionMap.put(userId,session);
        if (LOGGER.isDebugEnabled()){
            LOGGER.info(session.getId()+"connected");
        }
    }

    @OnMessage
    public void message(Session session, NoticeMessage noticeMessage) throws IOException, EncodeException {
        String receivedUserId =noticeMessage.getReceivedUserId();
        Map<String,Session> noticeSessionMap = noticeEndPoint.getNoticeSessionMap();
        Session chatSession= chatSessionMap.get(receivedUserId);
        Session noticeSession = noticeSessionMap.get(receivedUserId);
        noticeMessage.setDate(TimeUtil.generateShortDate(TimeUtil.getSimpleTime()));
        Chat chat = new Chat();
        LoginDto loginDto = (LoginDto) this.session.getAttribute("user");
        String halfId = loginDto.getHalf().getHalfId();
        chat.setHalfId(halfId);
        BeanUtils.copyProperties(noticeMessage,chat);
        chatRepository.insert(chat);
        if(noticeSession!=null){
            if(chatSession!=null){
                chatSession.getBasicRemote().sendObject(noticeMessage);
            }else{
                noticeSession.getBasicRemote().sendObject(noticeMessage);
            }
        }
    }

    @OnClose
    public void close(Session session, CloseReason closeReason, @PathParam("userId")String userId){
        try {
            session.close();
            chatSessionMap.remove(userId);
        } catch (IOException e) {
            LOGGER.error(session.getId()+"close socket failed");
        }
        LOGGER.info(closeReason.getReasonPhrase()+","+session.getId()+"close socket");
    }
    @OnError
    public void error(Throwable throwable,Session session,@PathParam("userId")String userId){
        try {
            session.close();
            chatSessionMap.remove(userId);
        } catch (IOException e) {
            LOGGER.error(session.getId()+"发生错误"+throwable.getMessage());
        }
    }
}
