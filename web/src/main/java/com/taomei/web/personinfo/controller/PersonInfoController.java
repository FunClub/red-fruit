package com.taomei.web.personinfo.controller;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.personinfo.serviceimpl.BasePersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/person-info")
public class PersonInfoController {
    private final BasePersonInfoService personInfoService;

    @Autowired
    public PersonInfoController(BasePersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    /**
     * 获取用户基本资料
     * @param session
     * @return 统一数据对象
     * @throws Exception
     */
    @GetMapping("/base-info")
    public ResultView selectBaseInfo(HttpSession session) throws Exception {
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        return personInfoService.selectPersonBaseInfo(loginDto.getUserId());
    }

    @PutMapping("/base-info")
    public ResultView updateBaseInfo(@RequestBody BaseUserInfoDto dto) throws Exception {
        return personInfoService.updatePersonBaseInfo(dto);
    }
}
