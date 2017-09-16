package com.taomei.service.personinfo.serviceimpl;

import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.service.personinfo.iservice.IPersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 用户资料模块的基本实现
 */
@Service
public class BasePersonInfoService implements IPersonInfoService {
    private final UserMapper userMapper;

    @Autowired
    public BasePersonInfoService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @CacheEvict(value = "app-cache",key = "#dto.halfId")
    @Override
    public boolean updateUserProfile(UpdateProfileDto dto) throws Exception {
        int count= userMapper.updateUserProfile(dto);
        if(count==0){
            throw new Exception("修改头像失败");
        }
        return true;
    }

    /**
     * 查询用户的基本信息
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    @Override
    public BaseUserInfoDto selectPersonBaseInfo(String userId) throws Exception {
        BaseUserInfoDto dto= userMapper.selectUserBaseInfoById(userId);
        if(dto==null){
            throw new Exception("获取基本资料失败");
        }
        return  dto;
    }

    @Override
    public boolean updatePersonBaseInfo(BaseUserInfoDto infoUser) throws Exception {
        int count = userMapper.updateUserBaseInfoById(infoUser);
        if(count!=1){
            throw new Exception("修改基本资料失败");

        }
        return  true;
    }
}
