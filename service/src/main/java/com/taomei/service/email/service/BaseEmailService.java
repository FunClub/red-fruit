package com.taomei.service.email.service;

import com.taomei.dao.entities.email.Email;
import com.taomei.dao.repository.EmailRepository;
import com.taomei.service.email.iservice.IEmailService;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmailService implements IEmailService {
    private final EmailRepository emailRepository;

    @Autowired
    public BaseEmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    /**
     * 插入邮件
     * @param email 邮件文档
     * @return 成功与否
     */
    @Override
    public boolean insertEmail(Email email) {
        email.setDate(TimeUtil.getSimpleTime());
        email.getEmailContents().get(0).setDate(email.getDate());
        emailRepository.insert(email);
        return true;
    }
}
