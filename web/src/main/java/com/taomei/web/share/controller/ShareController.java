package com.taomei.web.share.controller;

import com.taomei.service.utils.ResultViewUtil;
import com.taomei.dao.entities.ResultView;
import com.taomei.web.utils.ValidateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 共享控制器
 */
@RestController
@RequestMapping("/share")
public class ShareController {

    /**
     * 向浏览器输入验证码
     * @param request javax.servlet.{@link HttpServletRequest}
     * @param response javax.servlet.{@link HttpServletResponse}
     */
    @GetMapping("/verificationCodeImg")
    public void createVerificationCodeImg(HttpServletRequest request, HttpServletResponse response){
        ValidateUtil.createVerificationCodeImg(request,response);
    }

    /**
     *
     * @param verificationCode 用户输入的验证码
     * @param session javax.servlet.http.{@link HttpSession}
     * @return
     */
    @GetMapping("/{verificationCode}/verificationCode")
    public ResultView validateVerificationCode(@PathVariable("verificationCode") String verificationCode, HttpSession session){
        String actualCode = session.getAttribute("verificationCode").toString();
        boolean result = actualCode.equals(verificationCode);
        return  ResultViewUtil.success(result);
    }
}
