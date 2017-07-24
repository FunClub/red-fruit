package com.taomei.dao.dtos;

import com.taomei.dao.entities.Users.Users;

/**
 * 注册用户时的数据转输对象
 */
public class RegisterDto{
    private Users user;
    private String verificationCode;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
