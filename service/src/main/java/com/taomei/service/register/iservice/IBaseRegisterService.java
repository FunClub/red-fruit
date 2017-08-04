package com.taomei.service.register.iservice;

import com.taomei.dao.dtos.register.RegisterDto;
import com.taomei.dao.entities.ResultView;

/**
 * 基本的注册服务接口
 * com.taomei.dao.entities.ResultView：返回给浏览器的统一对象
 */
public interface IBaseRegisterService {
    /**
     * 判断账号是否能被注册
     * @param account 用户待注册的账号
     * @return
     */
    boolean canRegisterAble(String account);

    /**
     *
     * @param registerDto 待注册用户DTO
     * @return
     */
    boolean register(RegisterDto registerDto) throws Exception;
}
