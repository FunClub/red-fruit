package com.taomei.web.personinfo.controller;

import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.personinfo.serviceimpl.BasePersonInfoService;
import com.taomei.web.share.utils.ResultViewUtil;
import com.taomei.web.share.utils.UserUtil;
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
        return ResultViewUtil.success(personInfoService.selectPersonBaseInfo(UserUtil.getUserIdBySession(session)));
    }

    @PutMapping("/base-info")
    public ResultView updateBaseInfo(@RequestBody BaseUserInfoDto dto,HttpSession session) throws Exception {
        dto.setUserId(UserUtil.getUserIdBySession(session));
        return ResultViewUtil.success( personInfoService.updatePersonBaseInfo(dto));
    }

    /**
     * 更新用户头像
     * @param dto 用户头像
     * @param session
     * @return 统一数据对象
     * @throws Exception 头像修改失败
     */
    @PutMapping("/profile-img")
    public ResultView updateProfileImg(@RequestBody UpdateProfileDto dto, HttpSession session) throws Exception {
        dto.setUserId(UserUtil.getUserIdBySession(session));
        return ResultViewUtil.success(personInfoService.updateUserProfile(dto));
    }
}
