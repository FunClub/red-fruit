package com.taomei.web.websocket.serverendpoint;

import com.taomei.dao.repository.InvitationRepository;
import com.taomei.service.login.serviceimpl.BaseLoginService;
import com.taomei.web.configuration.CustomSpringConfigurator;
import com.taomei.web.websocket.InviteMessageType;
import com.taomei.web.websocket.decode.InviteMessageEncoder;
import com.taomei.web.websocket.encode.InviteMessageDecoder;
import com.taomei.web.websocket.message.InviteMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@ServerEndpoint(value="/invite/{userId}",configurator = CustomSpringConfigurator.class,
        encoders = {InviteMessageEncoder.class},
        decoders = {InviteMessageDecoder.class}
)
@Component
public class InviteEndPoint {
    private final  static Logger LOGGER = LoggerFactory.getLogger(InviteMessageDecoder.class);
    private Map<String,Session> inviteSessionMap;
    private final InvitationRepository invitationRepository;

    @Autowired
    public InviteEndPoint(InvitationRepository invitationRepository) {

        inviteSessionMap = new HashMap<>();
        this.invitationRepository = invitationRepository;
    }

    @OnOpen
    public void open(Session session, @PathParam("userId")String userId) throws IOException {
        this.inviteSessionMap.put(userId,session);
    }

    @OnMessage
    public void message(InviteMessage message, Session session) {
        Session invitedSession =inviteSessionMap.get(message.getInvitedId());
        if(message.getType()== InviteMessageType.INVITE.getType()){
            //保存邀请信息,如果对方没有被邀请的话
            InviteMessage newMessage=null;
            if(invitationRepository.findByInvitedId(message.getInvitedId())!=null){
                newMessage =invitationRepository.insert(message);
            }

            message.setStatus(newMessage!=null);
            try {
                //通知被邀请方有人邀请(如果在线)
                if( invitedSession!=null){
                    invitedSession.getBasicRemote().sendObject(message);
                }

                //通知发送方，消息的状态
                message.setType(InviteMessageType.REBACK.getType());
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                LOGGER.error("发送邀请信息时出错");
            }
        }

    }


}
