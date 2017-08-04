package com.taomei.service.home.serviceimpl;

import com.taomei.dao.dtos.home.HomeInfoDto;
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
     * @param userId 用户id
     * @return
     */
    @Override
    public HomeInfoDto getHomeInfo(String userId) throws Exception {
        HomeInfoDto homeInfoDto = userMapper.selectHomeInfo(userId);
        if(homeInfoDto==null){
            throw new Exception("获取主页信息失败");
        }
        return userMapper.selectHomeInfo(userId);
    }
}
