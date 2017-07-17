package com.taomei.service.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * 共享模块的工具类
 */
public class ShareUtil {

    /**
     * 将明文密码转换为加密密码
     * @param pass 明文密码
     * @return 加密后的密码
     */
    public static String generateEncryptPass(String pass){
        MessageDigest md5= null;
        String newPass=null;
        try {
            //确定计算方法
            md5 = MessageDigest.getInstance("MD5");
            //加密后的字符串
            BASE64Encoder base64en = new BASE64Encoder();
            newPass =base64en.encode(md5.digest(pass.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPass;
    }

}
