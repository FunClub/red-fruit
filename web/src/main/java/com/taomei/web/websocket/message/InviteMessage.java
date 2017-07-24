package com.taomei.web.websocket.message;

import com.taomei.dao.entities.invitation.Invitation;



/**
 * 邀请另一半消息
 */
public class InviteMessage extends Invitation {
    /**
     * 消息类型
     */
    private int type;

    /**
     * 消息的状态，是否操作成功
     */
    private boolean status;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
