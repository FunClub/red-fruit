package com.taomei.web.personinfo.controller;

import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.personinfo.serviceimpl.BasePersonInfoService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param userId
     * @return 统一数据对象
     * @throws Exception
     */
    @SetUserId
    @GetMapping("/base-info")
    public ResultView selectBaseInfo(String userId) throws Exception {
        return ResultViewUtil.success(personInfoService.selectPersonBaseInfo(userId));
    }
    @SetUserId
    @PutMapping("/base-info")
    public ResultView updateBaseInfo(String userId,@RequestBody BaseUserInfoDto dto) throws Exception {
        dto.setUserId(userId);
        return ResultViewUtil.success( personInfoService.updatePersonBaseInfo(dto));
    }

    /**
     * 更新用户头像
     * @param dto 用户头像
     * @return 统一数据对象
     * @throws Exception 头像修改失败
     */
    @SetUserId
    @PutMapping("/profile-img")
    public ResultView updateProfileImg(String userId,@RequestBody UpdateProfileDto dto) throws Exception {
        dto.setUserId(userId);
        return ResultViewUtil.success(personInfoService.updateUserProfile(dto));
    }
}
