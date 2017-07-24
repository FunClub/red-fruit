package com.taomei.web.websocket.serverendpoint;

import com.taomei.web.configuration.CustomSpringConfigurator;
import com.taomei.web.websocket.decode.InviteMessageEncoder;
import com.taomei.web.websocket.encode.InviteMessageDecoder;
import com.taomei.web.websocket.message.InviteMessage;
import org.springframework.stereotype.Component;


import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value="/invite/{userId}",configurator = CustomSpringConfigurator.class,
        encoders = {InviteMessageEncoder.class},
        decoders = {InviteMessageDecoder.class}
)
@Component
public class InviteEndPoint {
    private Map<String,Session> inviteSessionMap;

    public InviteEndPoint() {
        inviteSessionMap = new HashMap<>();
    }

    @OnOpen
    public void open(Session session, @PathParam("userId")String userId) throws IOException {
        System.out.println(userId+"连接成功...");
        this.inviteSessionMap.put(userId,session);
        session.getBasicRemote().sendText("连接成功");
    }

    @OnMessage
    public void message(InviteMessage message, Session session) throws IOException, EncodeException {
        Session invitedSession =inviteSessionMap.get(message.getInvitedId());
        invitedSession.getBasicRemote().sendObject(message);
    }


}
