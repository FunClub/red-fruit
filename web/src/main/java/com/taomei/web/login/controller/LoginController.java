package com.taomei.web.login.controller;

import com.taomei.dao.dtos.login.InvitationIdDto;
import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.service.login.iservice.ILoginService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final ILoginService baseLoginService;
    private final HttpSession session;
    @Autowired
    public LoginController(ILoginService baseLoginService, HttpSession session) {
        this.baseLoginService = baseLoginService;
        this.session = session;
    }

    /**
     *用户登录
     * @param users 保存用户的帐号密码
     * @return 统一结果对象
     */
    @PostMapping("/user")
    public ResultView login(@RequestBody Users users) throws Exception {
        LoginDto dto = baseLoginService.Login(users);
        session.setAttribute("user",dto);
        return ResultViewUtil.success(dto);
    }

    /**
     * 获取邀请另一半时用户的信息
     * @return 统一结果对象
     */
    @GetMapping("/user/invite")
    public ResultView getInviteUser(){
        LoginDto ld = (LoginDto) session.getAttribute("user");
        ld.setInvitations(baseLoginService.selectInvitation(ld.getUserId()));
        return ResultViewUtil.success(ld);
    }

    /**
     * 判断用户能否进入邀请另一半
     * @param userId
     * @return 统一结果对象
     */
    @SetUserId
    @GetMapping("/user/to-invite")
    public ResultView canToInvite(String userId){
            if(!baseLoginService.hasHalf(userId)){
                return ResultViewUtil.success(true);
            }
        return ResultViewUtil.success(false);
    }


    /**
     *判断对方能否被邀请
     * @param invitedId 被邀请用户的Id
     * @return 返回前台的统一数据对象
     */
    @SetUserId
    @GetMapping("/user/{invitedId}/can-invited")
    public ResultView canInvite(String userId,@PathVariable("invitedId")String invitedId) throws Exception {
        InvitationIdDto dto = new InvitationIdDto();
        dto.setInvitationId(userId);
        dto.setInvitedId(invitedId);
        return ResultViewUtil.success(baseLoginService.canInvite(dto));
    }

}
