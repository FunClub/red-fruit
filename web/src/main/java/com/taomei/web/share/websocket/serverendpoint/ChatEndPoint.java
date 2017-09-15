package com.taomei.web.share.websocket.serverendpoint;

import com.taomei.dao.entities.chat.Chat;
import com.taomei.service.chat.iservice.IChatService;
import com.taomei.service.share.utils.TimeUtil;
import com.taomei.web.share.configuration.CustomSpringConfigurator;
import com.taomei.web.share.websocket.decode.NoticeMessageEncoder;
import com.taomei.web.share.websocket.encode.NoticeMessageDecoder;
import com.taomei.web.share.websocket.message.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private final IChatService chatService;
    private final  static Logger LOGGER = LoggerFactory.getLogger(ChatEndPoint.class);
    private final HttpSession session;
    @Autowired
    public ChatEndPoint(NoticeEndPoint noticeEndPoint, @Qualifier("baseChatService") IChatService chatService, HttpSession session) {
        this.chatService = chatService;
        this.chatSessionMap = new HashMap<>();
        this.noticeEndPoint = noticeEndPoint;

        this.session = session;
    }

    @OnOpen
    public void open(Session session, @PathParam("userId")String userId) throws IOException {
        this.chatSessionMap.put(userId,session);
        LOGGER.info(userId+"connected");
    }

    @OnMessage
    public void message(Session session, NoticeMessage noticeMessage) throws IOException, EncodeException {
        String receivedUserId =noticeMessage.getReceivedUserId();
        Map<String,Session> noticeSessionMap = noticeEndPoint.getNoticeSessionMap();
        Session chatSession= chatSessionMap.get(receivedUserId);
        Session noticeSession = noticeSessionMap.get(receivedUserId);
        noticeMessage.setDate(TimeUtil.generateShortDate(TimeUtil.getSimpleTime()));
        Chat chat = new Chat();
        BeanUtils.copyProperties(noticeMessage,chat);
        chatService.insertChat(chat);

        if(noticeSession!=null){
            noticeMessage.setType("chat");
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
