package com.taomei.service.register.serviceimpl;

import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.service.register.iservice.IBaseRegisterService;
import com.taomei.service.utils.ResultViewStatusUtil;
import com.taomei.service.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册服务的基本实现
 */
@Service
public class BaseRegisterService implements IBaseRegisterService {
    private final UserMapper userMapper;

    @Autowired
    public BaseRegisterService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     *判断账号是否能被注册。
     * 能注册就返回date=true,否则
     * @param account 用户待注册的账号
     * @return 返回给浏览器的统一数据对象
     */
    @Override
    public ResultView canRegisterAble(String account) {
        if( userMapper.selectAccountByAccount(account)==null){
           return ResultViewUtil.success(true);
        }else{
            return ResultViewUtil.error(ResultViewStatusUtil.EXISTS.getCode(),ResultViewStatusUtil.EXISTS.getMessage());
        }

    }
}
