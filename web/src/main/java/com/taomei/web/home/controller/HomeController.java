package com.taomei.web.home.controller;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.home.iservice.IHomeService;
import com.taomei.service.login.iservice.ILoginService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
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
    private final IHomeService homeService;

    @Autowired
    public HomeController(@Qualifier("baseLoginService") ILoginService loginService,
                          @Qualifier("baseHomeService")IHomeService homeService) {
        this.loginService = loginService;
        this.homeService = homeService;
    }

    /**
     * 判断用户能否进入home界面
     * 登录且有另一半才能进入
     * @param userId
     * @return 统一数据对象
     */
    @GetMapping("/can-to")
    @SetUserId
    public ResultView canToHome(String userId){
            if (loginService.hasHalf(userId)){
                return ResultViewUtil.success(true);
            }
        return ResultViewUtil.success(false);
    }

    /**
     * 获取用户的主页信息
     * @param session
     * @return 统一数据对象
     */
    @GetMapping("/info")
    public ResultView getHomeInfo(HttpSession session) throws Exception {
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto.getHalf()==null){//如果用户是称为情侣再进入主界面就需要设置half对象
            loginDto.setHalf(loginService.getHalf(loginDto.getUserId()));
        }
        return ResultViewUtil.success(homeService.getHomeInfo(loginDto));
    }
}
