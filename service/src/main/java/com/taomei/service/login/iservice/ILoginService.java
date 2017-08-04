package com.taomei.service.login.iservice;

import com.taomei.dao.dtos.login.InvitationIdDto;
import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.Half;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.entities.Invitation;

import java.util.List;

/**
 * 登录模块的基本接口
 */
public interface ILoginService {
    /**
     * 获取用户另一半对象
     * @param userId 用户id
     * @return 另一半对象
     */
    Half getHalf(String userId);
    /**
     *用户登录
     * @param users 用户帐号密码
     * @return 登录dto
     */
    LoginDto Login(Users users) throws Exception;

    /**
     *判断另一半是否能被邀请
     * @param dto 邀请和被邀请id dto
     * @return
     */
    boolean canInvite(InvitationIdDto dto) throws Exception;

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
     boolean hasHalf(String userId);
}
