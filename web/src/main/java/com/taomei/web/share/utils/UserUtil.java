package com.taomei.web.share.utils;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.Half;

import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 */
public class UserUtil {

    /**
     * 通过session获取用户id
     * @param session
     * @return 用户id
     */
    public static String getUserIdBySession(HttpSession session){
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto!=null){
            return loginDto.getUserId();
        }
        return null;
    }

    /**
     * 通过session获取half
     * @param session
     * @return half
     */
    public static Half getHalfBySession(HttpSession session){
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto!=null){
            return loginDto.getHalf();
        }
        return null;
    }

    /**
     * 根据session获取情侣id
     * @param session
     * @return
     */
    public static  String getHalfIdBySession(HttpSession session){
        Half half =getHalfBySession(session);
        if(half!=null){
            return half.getHalfId();
        }
        return null;
    }
}
