package com.taomei.dao.dtos;

import java.math.BigInteger;

/**
 * 登录时的用户
 */
public class LoginDto {
    private BigInteger userId;
    /**
     * 没有另一半时nickName才会有值
     */
    private String nickname;

}
