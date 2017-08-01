package com.taomei.web.home.controller;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.repository.HalfRepository;
import com.taomei.service.login.iservice.ILoginService;
import com.taomei.service.utils.ResultViewStatusUtil;
import com.taomei.service.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * home模块控制器
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    private final ILoginService loginService;

    @Autowired
    public HomeController(@Qualifier("baseLoginService") ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 判断用户能否进入home界面
     * 登录且有另一半才能进入
     * @param session
     * @return
     */
    @GetMapping("/can-to")
    public ResultView canToHome(HttpSession session){
        LoginDto dto = (LoginDto) session.getAttribute("user");
        if(dto!=null){
            if (loginService.hasHalf(dto.getUserId())){
                return ResultViewUtil.success(true);
            }
        }
        return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
    }
}
