package com.taomei.service.home.serviceimpl;

import com.taomei.dao.dtos.home.HomeInfoDto;
import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.Half;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.service.home.iservice.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户主页服务的基本实现
 */
@Service
public class BaseHomeService implements IHomeService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 获得用户主页信息
     * @param dto 用户id
     * @return
     */
    @Override
    public HomeInfoDto getHomeInfo(LoginDto dto) throws Exception {
        String userId = dto.getUserId();
        Half half = dto.getHalf();
        HomeInfoDto homeInfoDto = userMapper.selectHomeInfo(userId);
        String halfUserId=half.getUserId1().equals(userId)?half.getUserId2():half.getUserId1();
        HomeInfoDto homeInfoDto1 = userMapper.selectHomeInfo(halfUserId);
        homeInfoDto.setHalfNickname(homeInfoDto1.getNickname());
        homeInfoDto.setHalfProfileImg(homeInfoDto1.getProfileImg());
        homeInfoDto.setHalfUserId(halfUserId);
        homeInfoDto.setHalfId(dto.getHalf().getHalfId());
        return homeInfoDto;
    }
}
