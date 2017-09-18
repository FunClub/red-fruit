package com.taomei.web.email.controller;

import com.taomei.dao.dtos.email.SelectEmailCatalogDto;
import com.taomei.dao.dtos.email.SelectEmailDto;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.email.Email;
import com.taomei.service.email.iservice.IEmailService;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final IEmailService emailService;

    @Autowired
    public EmailController(@Qualifier("baseEmailService") IEmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 查询邮件
     * @param userId 用户id
     * @param emailId 邮件id
     * @return 统一数据对象
     */
    @GetMapping("/{emailId}")
    @SetUserId
    public ResultView selectEmail(String userId,@PathVariable("emailId") String emailId){
        SelectEmailDto dto = new SelectEmailDto();
        dto.setUserId(userId);
        dto.setEmailId(emailId);
        return ResultViewUtil.success(emailService.selectEmail(dto));
    }
    /**
     * 查询邮件目录
     * @param userId 用户id
     * @param dto 查询邮件目录dto
     * @return 统一数据对象
     */
    @PostMapping("/catalog")
    @SetUserId
    public ResultView selectEmailCatalog(String userId, @RequestBody SelectEmailCatalogDto dto){
        dto.setUserId(userId);
        return ResultViewUtil.success(emailService.selectEmailCatalog(dto));
    }
    /**
     * 查询收件人信息
     * @param userId 用户id
     * @return 统一数据对象
     */
    @GetMapping("/{userId}/receive-user")
    public ResultView selectReceivedUser(@PathVariable("userId") String userId){
        return ResultViewUtil.success(emailService.selectReceivedUser(userId));
    }
    /**
     * 插入邮件
     * @param userId 用户id
     * @param email 邮件
     * @return 统一数据对象
     */
    @PostMapping("/")
    @SetUserId
    public ResultView insertEmail(String userId,@RequestBody Email email){
        email.setFromUserId(userId);
        email.getEmailContents().get(0).setUserId(userId);
        return ResultViewUtil.success(emailService.insertEmail(email));
    }
}
