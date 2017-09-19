package com.taomei.service.email.service;

import com.taomei.dao.dtos.email.*;
import com.taomei.dao.dtos.share.user.AttentionUserDto;
import com.taomei.dao.entities.email.Email;
import com.taomei.dao.entities.email.EmailContent;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.EmailRepository;
import com.taomei.service.email.iservice.IEmailService;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BaseEmailService implements IEmailService {
    private final EmailRepository emailRepository;
    private final UserMapper userMapper;
    private final MongoOperations mongoOperations;
    @Autowired
    public BaseEmailService(EmailRepository emailRepository, UserMapper userMapper, MongoOperations mongoOperations) {
        this.emailRepository = emailRepository;
        this.userMapper = userMapper;
        this.mongoOperations = mongoOperations;
    }

    /**
     * 删除邮件
     * @param emailIds 邮件id集合
     * @return 成功与否
     */
    @Override
    public boolean deleteEmail(List<String> emailIds) {
        Query query  = Query.query(where("emailId").in(emailIds));
        return mongoOperations.remove(query,Email.class,"email").getN()==emailIds.size();
    }

    /**
     * 插入邮件内容
     * @param dto 插入dto
     * @return 成功与否
     */
    @Override
    public boolean insertEmailContent(InsertEmailContentDto dto) {
        dto.getEmailContent().setDate(TimeUtil.getSimpleTime());
        String emailId = dto.getEmailId();
        EmailContent emailContent = dto.getEmailContent();
        String userId = emailContent.getUserId();
        Email email = emailRepository.findOne(emailId);
        Query query = Query.query(where("emailId").is(emailId));
        Update update = new Update();
        if(userId.equals(email.getFromUserId())){
            update.set("receiveState",false);
        }else{
            update.set("fromState",false);
        }
        update.set("date",TimeUtil.getSimpleTime());
        update.addToSet("emailContents",emailContent);
        return mongoOperations.updateFirst(query,update,Email.class,"email").getN()>0;
    }

    /**
     * 查询邮件dto
     *
     * @param dto 查询条件
     * @return 显示一个邮件的dto
     */
    @Override
    public ShowEmailDto selectEmail(SelectEmailDto dto) {
        ShowEmailDto showEmailDto  = new ShowEmailDto();
        String userId = dto.getUserId();
        String emailId = dto.getEmailId();
        Email email = emailRepository.findOne(emailId);
        String emailUserId;
        Query query = Query.query(where("emailId").is(emailId));
        Update update = new Update();
        if(userId.equals(email.getFromUserId())){
            emailUserId=email.getReceiveUserId();
            update.set("fromState",true);
        }else{
            emailUserId=email.getFromUserId();
            update.set("receiveState",true);
        }
         //改为已读
        mongoOperations.updateFirst(query,update,Email.class,"email");
        showEmailDto.setEmailUserId(emailUserId);
        showEmailDto.setEmailId(email.getEmailId());
        AttentionUserDto attentionUserDto = userMapper.selectAttentionUserDto(emailUserId);
        BeanUtils.copyProperties(attentionUserDto,showEmailDto);
        List<ShowEmailContentDto> emailContentDtos = new ArrayList<>();
        List<EmailContent> emailContents = email.getEmailContents();
        ShowEmailContentDto showEmailContentDto;
        if( showEmailDto.getSex().equals("1")){
            showEmailDto.setSex("男");
        }else {
            showEmailDto.setSex("女");
        }
        for (EmailContent emailContent:emailContents){
            showEmailContentDto = new ShowEmailContentDto();
            AttentionUserDto emailContentUser = userMapper.selectAttentionUserDto(emailContent.getUserId());
            BeanUtils.copyProperties(emailContentUser,showEmailContentDto);
            showEmailContentDto.setContent(emailContent.getContent());
            showEmailContentDto.setDate(email.getDate());
            emailContentDtos.add(showEmailContentDto);
        }
        showEmailDto.setEmailContents(emailContentDtos);


        return showEmailDto;
    }

    /**
     * 查询邮件目录
     * @return 邮件目录集合
     */
    @Override
    public ShowPagedEmailDto<ShowEmailCatalogDto> selectEmailCatalog(SelectEmailCatalogDto dto) {
        ShowPagedEmailDto<ShowEmailCatalogDto> showPagedArtDto = new ShowPagedEmailDto<>();
        String userId=dto.getUserId();
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(),dto.getPageSize(),new Sort(Sort.Direction.DESC,"date"));
       /* List<Email> emails = emailRepository.findByFromUserIdOrReceiveUserIdOrderByDateDesc(userId,userId);*/
        Email emailEx=new Email();emailEx.setFromUserId(userId);emailEx.setReceiveUserId(userId);
        Page<Email> emailPage = emailRepository.findAll(Example.of(emailEx,ExampleMatcher.matchingAny()),pageRequest);
        List<Email> emails = emailPage.getContent();
        List<ShowEmailCatalogDto> showEmailCatalogDtos = new ArrayList<>();
        ShowEmailCatalogDto showEmailCatalogDto;
        for(Email email:emails){
            showEmailCatalogDto = new ShowEmailCatalogDto();
            //判断我是发送方还是接收方
            String emailUserId;
            String whoSend;
            boolean isMeSend=false;
            if(userId.equals(email.getFromUserId())){
                emailUserId=email.getReceiveUserId();
                whoSend="我发出的邮件";
                isMeSend=true;
                showEmailCatalogDto.setState(email.getFromState());
            }else{
                emailUserId=email.getFromUserId();
                whoSend="发给我的邮件";
                showEmailCatalogDto.setState(email.getReceiveState());
            }

            //填充数据
            AttentionUserDto attentionUserDto =userMapper.selectAttentionUserDto(emailUserId);
            BeanUtils.copyProperties(attentionUserDto,showEmailCatalogDto);
            if( showEmailCatalogDto.getSex().equals("1")){
                showEmailCatalogDto.setSex("男");
            }else {
                showEmailCatalogDto.setSex("女");
            }
            showEmailCatalogDto.setHowLongAgo(TimeUtil.calculateHowLongAgo(email.getDate()));
            showEmailCatalogDto.setEmailContentSize((long) email.getEmailContents().size());
            showEmailCatalogDto.setWhoSend(whoSend);
            showEmailCatalogDto.setEmailId(email.getEmailId());
            showEmailCatalogDtos.add(showEmailCatalogDto);
        }
        showPagedArtDto.setContent(showEmailCatalogDtos);
        showPagedArtDto.setTotalElements(emailPage.getTotalElements());

        /*计算未读条数*/
        List<Email> unReadEmails = emailRepository.findAll(Example.of(emailEx,ExampleMatcher.matchingAny()));
        int count=0;
        for (Email email:unReadEmails){
            boolean isMeSend=false;
            if(userId.equals(email.getFromUserId())){
                isMeSend=true;
            }
            if(isMeSend){
                if(!email.getFromState()){
                    count++;
                }
            }else{
                if(!email.getReceiveState()){
                    count++;
                }
            }
        }
        showPagedArtDto.setUnReadCount(count);
        return showPagedArtDto;
    }

    /**
     * 查询收件人信息
     * @param userId 收件人id
     * @return
     */
    public AttentionUserDto selectReceivedUser(String userId){
        AttentionUserDto attentionUserDto=userMapper.selectAttentionUserDto(userId);
        if( attentionUserDto.getSex().equals("1")){
            attentionUserDto.setSex("男");
        }else {
            attentionUserDto.setSex("女");
        }
        return attentionUserDto;
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
