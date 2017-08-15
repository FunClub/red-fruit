package com.taomei.web.share.websocket.serverendpoint;

import com.taomei.web.share.configuration.CustomSpringConfigurator;

import com.taomei.web.share.websocket.decode.NoticeMessageEncoder;
import com.taomei.web.share.websocket.encode.NoticeMessageDecoder;
import com.taomei.web.share.websocket.message.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理通知信息的socket
 */
@ServerEndpoint(value="/notice/{userId}",configurator = CustomSpringConfigurator.class,
        encoders = {NoticeMessageEncoder.class},
        decoders = {NoticeMessageDecoder.class}
)
@Component
public class NoticeEndPoint {
    private final  static Logger LOGGER = LoggerFactory.getLogger(NoticeEndPoint.class);
    /**
     * 保存会话
     */
    private Map<String,Session> noticeSessionMap;

    public NoticeEndPoint() {
        this.noticeSessionMap = new HashMap<>();
    }

    @OnOpen
    public void open(Session session, @PathParam("userId")String userId) throws IOException {
        this.noticeSessionMap.put(userId,session);
        if (LOGGER.isDebugEnabled()){
            LOGGER.info(session.getId()+"connected");
        }
    }
    @OnMessage
    public void message(Session session, NoticeMessage noticeMessage) {
        String recevieUserId = noticeMessage.getReceivedUserId();
        Session receiveUserSession = noticeSessionMap.get(recevieUserId);
        try {
            receiveUserSession.getBasicRemote().sendObject(noticeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            if (LOGGER.isDebugEnabled()){
                LOGGER.error("发送通知消息失败");
            }
        }
    }
}
