package com.taomei.service.email.iservice;

import com.taomei.dao.dtos.email.*;
import com.taomei.dao.dtos.share.user.AttentionUserDto;
import com.taomei.dao.entities.email.Email;

/**
 * 邮件接口服务
 */
public interface IEmailService {

    /**
     * 查询邮件dto
     * @param dto 查询条件
     * @return  显示一个邮件的dto
     */
    ShowEmailDto selectEmail(SelectEmailDto dto);
    /**
     * 查询邮件目录
     * @param dto 查询dto
     * @return 邮件目录集合
     */
    ShowPagedEmailDto<ShowEmailCatalogDto> selectEmailCatalog(SelectEmailCatalogDto dto);
    /**
     * 插入邮件
     * @param email 邮件文档
     * @return 成功与否
     */
    boolean insertEmail(Email email);
    /**
     * 查询收件人信息
     * @param userId 收件人id
     * @return
     */
    AttentionUserDto selectReceivedUser(String userId);
}
