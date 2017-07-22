package com.taomei.service.login.iservice;

import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;

/**
 * 登录模块的基本接口
 */
public interface IBaseLoginService {

    public ResultView Login(Users users);
}
