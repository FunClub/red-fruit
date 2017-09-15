package com.taomei.web.personinfo.controller;

import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.personinfo.serviceimpl.BasePersonInfoService;
import com.taomei.service.share.ImageService;
import com.taomei.service.share.enums.FileFolder;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/person-info")
public class PersonInfoController {
    private final BasePersonInfoService personInfoService;
    private final ImageService imageService;
    @Autowired
    public PersonInfoController(BasePersonInfoService personInfoService, ImageService imageService) {
        this.personInfoService = personInfoService;
        this.imageService = imageService;
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
     * @param originalProfileImg 原始头像 dataurl
     * @return 统一数据对象
     * @throws Exception 头像修改失败profileImg
     */
    @SetUserId
    @PutMapping("/profile-img")
    public ResultView updateProfileImg(String userId, MultipartFile profileImgFile,
                                       String originalProfileImg,String oldProfileImg) throws Exception {
        if(!oldProfileImg.equals("static/defaultMeImg.png")){
            imageService.deleteImg(oldProfileImg);
        }
        String filePath=imageService.generateImgPath(profileImgFile, FileFolder.PROFILE.getFolder());

        UpdateProfileDto dto = new UpdateProfileDto();
        dto.setUserId(userId);
        dto.setOriginalProfileImg(originalProfileImg);
        dto.setProfileImg(filePath);
        if(!personInfoService.updateUserProfile(dto)){throw new Exception("更新头像失败");}
        return ResultViewUtil.success(filePath);
    }
}
