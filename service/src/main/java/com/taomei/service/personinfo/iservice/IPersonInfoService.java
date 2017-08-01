package com.taomei.service.personinfo.iservice;

import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.entities.ResultView;


/**
 * 用户资料接口
 */
public interface IPersonInfoService {

    /**
     * 更新用户头像
     * @param dto 用户头像
     * @return
     */
    ResultView updateUserProfile(UpdateProfileDto dto) throws Exception;
    /**
     * 查询用户的基本资料
     * @param userId 用户id
     * @return 统一数据对象
     */
    ResultView selectPersonBaseInfo(String userId) throws Exception;

    /**
     * 更新用户基本资料
     * @param infoUser 待更新的用户数据
     * @return 统一数据对象
     */
    ResultView updatePersonBaseInfo(BaseUserInfoDto infoUser) throws Exception;

}
