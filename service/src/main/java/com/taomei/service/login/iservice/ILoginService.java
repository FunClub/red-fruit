package com.taomei.service.login.iservice;

import com.taomei.dao.dtos.login.InvitationIdDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.entities.Invitation;

import java.util.List;

/**
 * 登录模块的基本接口
 */
public interface ILoginService {
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

    /**
     * 查询邀请信息
     * @param userId 用户Id
     * @return
     */
    List<Invitation> selectInvitation(String userId);
    /**
     * 判断用户是否有另一半
     * @param userId 用户id
     * @return true有，false无
     */
    public boolean hasHalf(String userId);
}
