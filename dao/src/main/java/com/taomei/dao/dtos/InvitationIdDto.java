package com.taomei.dao.dtos;

import java.math.BigInteger;

/**
 *存放查询是否可邀请的id
 */
public class InvitationIdDto {
    /**
     * 被邀请者id
     */
    private String invitedId;

    /**
     * 邀请者id
     */
    private String invitationId;

    public String getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }
}
