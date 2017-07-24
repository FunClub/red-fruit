package com.taomei.dao.dtos;

import java.math.BigInteger;

/**
 *存放查询是否可邀请的id
 */
public class InvitationIdDto {
    /**
     * 被邀请者id
     */
    private BigInteger invitedId;

    /**
     * 邀请者id
     */
    private BigInteger invitationId;

    public BigInteger getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(BigInteger invitedId) {
        this.invitedId = invitedId;
    }

    public BigInteger getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(BigInteger invitationId) {
        this.invitationId = invitationId;
    }
}
