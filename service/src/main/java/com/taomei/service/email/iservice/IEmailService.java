package com.taomei.service.email.iservice;

import com.taomei.dao.entities.email.Email;

/**
 * 邮件接口服务
 */
public interface IEmailService {
    /**
     * 插入邮件
     * @param email 邮件文档
     * @return 成功与否
     */
    boolean insertEmail(Email email);
}
