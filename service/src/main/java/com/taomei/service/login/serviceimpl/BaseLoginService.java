package com.taomei.service.login.serviceimpl;

import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.service.login.iservice.IBaseLoginService;
import com.taomei.service.utils.ResultViewStatusUtil;
import com.taomei.service.utils.ResultViewUtil;
import com.taomei.service.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseLoginService implements IBaseLoginService {
    @Autowired//
    private UserMapper userMapper;
    @Override
    public ResultView Login(Users users) {
        String acPassword = users.getPasswords();
        users.setPasswords(ShareUtil.generateEncryptPass(acPassword));
        Users newUser = userMapper.selectUserByAccountAndPassword(users);
        if(newUser!=null){
            if(newUser.getHalfId()!=null){

            }else{

            }
           return ResultViewUtil.success(newUser);
        }else {
            return ResultViewUtil.error(ResultViewStatusUtil.UNKNOWN.getCode(),ResultViewStatusUtil.UNKNOWN.getMessage());
        }
    }
}
