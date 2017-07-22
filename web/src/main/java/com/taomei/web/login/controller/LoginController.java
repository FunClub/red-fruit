package com.taomei.web.login.controller;

import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.service.login.iservice.IBaseLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final IBaseLoginService baseLoginService;

    @Autowired
    public LoginController(IBaseLoginService baseLoginService) {
        this.baseLoginService = baseLoginService;
    }

    @PostMapping("/user")
    public ResultView login(@RequestBody Users users){
        return baseLoginService.Login(users);
    }
}
