package com.taomei.service.register.serviceimpl;

import com.taomei.dao.dtos.register.RegisterDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.service.register.iservice.IBaseRegisterService;
import com.taomei.service.share.utils.RegisterUtil;

import com.taomei.service.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册服务的基本实现
 * return 返回给浏览器的统一数据对象
 */
@Service
public class BaseRegisterService implements IBaseRegisterService {
    private final UserMapper userMapper;

    @Autowired
    public BaseRegisterService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     *用户注册
     * @param registerDto 待注册用户DTO
     */
    @Override
    public boolean register(RegisterDto registerDto) throws Exception {
        Users users = registerDto.getUser();
        /*将密码加密*/
        String encryptPass = ShareUtil.generateEncryptPass(users.getPasswords());
        users.setPasswords(encryptPass);
        /*设置默认的随机昵称*/
        users.setNickname(RegisterUtil.generateRandomNickName());
        users.setProfileImg("assets/img/defaultMeImg.png");
        users.setOriginalProfileImg("assets/img/defaultMeImg.png");
        int count = userMapper.insert(users);
        if(count>0){
            return true;
        }else{
            throw new Exception("注册失败！");
        }
    }

    /**
     *判断账号是否能被注册。
     * 能注册就返回date=true,否则
     * @param account 用户待注册的账号
     */
    @Override
    public boolean canRegisterAble(String account) {
        if( userMapper.selectAccountByAccount(account)==null){
           return true;
        }else{
            return false;
        }

    }


}
