package com.taomei.web.register.controller;

import com.taomei.service.register.iservice.IBaseRegisterService;
import com.taomei.web.utils.LoginUtil;
import com.taomei.dao.entities.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注册控制器
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Qualifier("baseRegisterService")
    private final IBaseRegisterService baseRegisterService;

    @Autowired
    public RegisterController(IBaseRegisterService baseRegisterService) {
        this.baseRegisterService = baseRegisterService;
    }

    /**
     * 向浏览器输入验证码
     * @param request javax.servlet.{@link HttpServletRequest}
     * @param response javax.servlet.{@link HttpServletResponse}
     */
    @GetMapping("/verificationCodeImg")
    public void createVerificationCodeImg(HttpServletRequest request, HttpServletResponse response){
        LoginUtil.createVerificationCodeImg(request,response);
    }

    /**
     *
     * @param account 用户账号
     * @return 返回给浏览器的统一数据对象
     */
    @GetMapping("{account}/account")
    public ResultView selectAccountByAccount(@PathVariable("account") String account){
        return baseRegisterService.canRegisterAble(account);
    }
}
