package com.taomei.dao.entities.half;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * 情侣文档
 */
@Document(collection = "half")
public class Half {
    @Id
    private BigInteger halfId;
    private BigInteger userId1;
    private BigInteger userId2;

    public BigInteger getHalfId() {
        return halfId;
    }

    public void setHalfId(BigInteger halfId) {
        this.halfId = halfId;
    }

    public BigInteger getUserId1() {
        return userId1;
    }

    public void setUserId1(BigInteger userId1) {
        this.userId1 = userId1;
    }

    public BigInteger getUserId2() {
        return userId2;
    }

    public void setUserId2(BigInteger userId2) {
        this.userId2 = userId2;
    }
}
