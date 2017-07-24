package com.taomei.service.login.iservice;

import com.taomei.dao.dtos.InvitationIdDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;

import java.math.BigInteger;

/**
 * 登录模块的基本接口
 */
public interface IBaseLoginService {
    /**
     *用户登录
     * @param users 用户帐号密码
     * @return 返回前台的统一数据对象
     */
    ResultView Login(Users users);

    /**
     *判断另一半是否能被邀请
     * @param dto 邀请和被邀请id dto
     * @return 返回前台的统一数据对象
     */
    ResultView canInvite(InvitationIdDto dto);
}
