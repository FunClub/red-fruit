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
    private String userId1;
    private String userId2;

    public BigInteger getHalfId() {
        return halfId;
    }

    public void setHalfId(BigInteger halfId) {
        this.halfId = halfId;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

}