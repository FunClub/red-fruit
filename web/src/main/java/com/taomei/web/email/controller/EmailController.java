package com.taomei.web.email.controller;

import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.email.Email;
import com.taomei.service.email.iservice.IEmailService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final IEmailService emailService;

    @Autowired
    public EmailController(@Qualifier("baseEmailService") IEmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 插入邮件
     * @param userId 用户id
     * @param email 邮件
     * @return 统一数据对象
     */
    @PostMapping("/")
    @SetUserId
    public ResultView insertEmail(String userId,Email email){
        email.setFromUserId(userId);
        email.getEmailContents().get(0).setUserId(userId);
        return ResultViewUtil.success(emailService.insertEmail(email));
    }
}
