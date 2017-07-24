package com.taomei.web.websocket;

public enum InviteMessageType {
    /**
     * 发送消息后系统回复
     */
    REBACK(0),
    /**
     *邀请另一半
     */
    INVITE(1),
    /**
     * 同意邀请
     */
    AGREE(2);
    private int type;

    InviteMessageType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
