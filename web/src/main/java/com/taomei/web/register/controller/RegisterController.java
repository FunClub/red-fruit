package com.taomei.web.register.controller;

import com.taomei.dao.dtos.register.RegisterDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.register.iservice.IBaseRegisterService;
import com.taomei.web.share.utils.ResultViewStatusUtil;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Qualifier("baseRegisterService")
    private final IBaseRegisterService baseRegisterService;

    @Autowired
    public RegisterController(IBaseRegisterService baseRegisterService) {
        this.baseRegisterService = baseRegisterService;
    }
    @PostMapping(value = "/user")
    public ResultView register(@RequestBody RegisterDto registerDto,  HttpSession session) throws Exception {
        String actualCode = (String) session.getAttribute("verificationCode");
        boolean result = actualCode.equals(registerDto.getVerificationCode());
        /*如果验证码无误*/
        if (result){
            return ResultViewUtil.success(baseRegisterService.register(registerDto));
        }
        throw new Exception("注册失败");
    }

   /**
     * 验证用户账号是否被注册
     * @param account 用户账号
     * @return 返回给浏览器的统一数据对象
     */
    @GetMapping("{account}/account")
    public ResultView selectAccountByAccount(@PathVariable("account") String account){
        return ResultViewUtil.success(baseRegisterService.canRegisterAble(account));
    }
}
