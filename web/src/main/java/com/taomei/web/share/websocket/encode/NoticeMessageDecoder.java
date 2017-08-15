package com.taomei.web.share.websocket.encode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taomei.web.share.websocket.message.InviteMessage;
import com.taomei.web.share.websocket.message.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class NoticeMessageDecoder implements Decoder.Text<NoticeMessage> {
    private final  static Logger LOGGER = LoggerFactory.getLogger(InviteMessageDecoder.class);
    @Override
    public NoticeMessage decode(String s) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        NoticeMessage noticeMessage =null;
        try {
            noticeMessage =objectMapper.readValue(s, NoticeMessage.class);
        } catch (IOException e) {
            LOGGER.error("json字符串=》java对象，解析失败");
            e.printStackTrace();
        }
        return noticeMessage;
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
