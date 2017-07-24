package com.taomei.dao.entities.invitation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "invitation")
public class Invitation {
    @Id
    private BigInteger invitationId;
    private String inviteId;
    private String invitedId;
    private String nickname;
    private String profileImg;

    public BigInteger getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(BigInteger invitationId) {
        this.invitationId = invitationId;
    }

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }

    public String getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}