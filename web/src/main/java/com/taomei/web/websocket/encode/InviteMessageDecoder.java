package com.taomei.web.websocket.encode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taomei.web.websocket.message.InviteMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

/**
 * 邀请消息解码器，将json字符串转换为Java对象
 */
public class InviteMessageDecoder implements Decoder.Text<InviteMessage> {
    private final  static Logger LOGGER = LoggerFactory.getLogger(InviteMessageDecoder.class);
    @Override
    public InviteMessage decode(String s) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        InviteMessage chatMessage =null;
        try {
            chatMessage =objectMapper.readValue(s, InviteMessage.class);
        } catch (IOException e) {
                LOGGER.error("json字符串=》java对象，解析失败");
            e.printStackTrace();
        }
        return chatMessage;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
