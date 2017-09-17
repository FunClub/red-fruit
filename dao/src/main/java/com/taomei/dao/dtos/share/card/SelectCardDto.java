package com.taomei.dao.dtos.share.card;

/**
 * 查询名片的dto
 */
public class SelectCardDto {
    private String userId;
    private String cardUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardUserId() {
        return cardUserId;
    }

    public void setCardUserId(String cardUserId) {
        this.cardUserId = cardUserId;
    }
}
