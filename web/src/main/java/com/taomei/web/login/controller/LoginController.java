package com.taomei.web.login.controller;

import com.taomei.dao.dtos.login.InvitationIdDto;
import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.service.login.iservice.ILoginService;
import com.taomei.web.share.utils.ResultViewStatusUtil;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final ILoginService baseLoginService;

    @Autowired
    public LoginController(ILoginService baseLoginService) {
        this.baseLoginService = baseLoginService;
    }

    /**
     *用户登录
     * @param users 保存用户的帐号密码
     * @param session 存放用户对象（有id，如果有nickname那说明没有另一半）
     * @return 统一结果对象
     */
    @PostMapping("/user")
    public ResultView login(@RequestBody Users users, HttpSession session) throws Exception {
        LoginDto dto = baseLoginService.Login(users);
        session.setAttribute("user",dto);
        return ResultViewUtil.success(dto);
    }

    /**
     * 获取邀请另一半时用户的信息
     * @param session
     * @return 统一结果对象
     */
    @GetMapping("/user/invite")
    public ResultView getInviteUser(HttpSession session){
        LoginDto ld = (LoginDto) session.getAttribute("user");
        ld.setInvitations(baseLoginService.selectInvitation(ld.getUserId()));
        return ResultViewUtil.success(ld);
    }

    /**
     * 判断用户能否进入邀请另一半
     * @param session
     * @return 统一结果对象
     */
    @GetMapping("/user/to-invite")
    public ResultView canToInvite(HttpSession session){
        LoginDto ld = (LoginDto)session.getAttribute("user");
        if(ld!=null){
            if(!baseLoginService.hasHalf(ld.getUserId())){
                return ResultViewUtil.success(true);
            }
        }
        return ResultViewUtil.success(false);
    }


    /**
     *判断对方能否被邀请
     * @param userId 被邀请用户的Id
     * @return 返回前台的统一数据对象
     */
    @GetMapping("/user/{userId}/can-invited")
    public ResultView canInvite(@PathVariable("userId")String userId,HttpSession session) throws Exception {
        LoginDto ld = (LoginDto)session.getAttribute("user");
        InvitationIdDto dto = new InvitationIdDto();
        dto.setInvitationId(ld.getUserId());
        dto.setInvitedId(userId);
        return ResultViewUtil.success(baseLoginService.canInvite(dto));
    }

}
