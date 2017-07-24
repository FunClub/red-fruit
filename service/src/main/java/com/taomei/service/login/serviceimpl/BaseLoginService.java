package com.taomei.service.login.serviceimpl;

import com.taomei.dao.dtos.InvitationIdDto;
import com.taomei.dao.dtos.LoginDto;
import com.taomei.dao.entities.half.Half;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users.Users;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.HalfRepository;
import com.taomei.dao.repository.InvitationRepository;
import com.taomei.service.login.iservice.IBaseLoginService;
import com.taomei.service.utils.ResultViewStatusUtil;
import com.taomei.service.utils.ResultViewUtil;
import com.taomei.service.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BaseLoginService implements IBaseLoginService {

    private final UserMapper userMapper;

    private final HalfRepository halfRepository;
    private final InvitationRepository invitationRepository;
    private final MongoOperations mongoOperations;
    @Autowired
    public BaseLoginService(InvitationRepository invitationRepository, UserMapper userMapper, HalfRepository halfRepository, InvitationRepository invitationRepository1, MongoOperations mongoOperations) {
        this.userMapper = userMapper;
        this.halfRepository = halfRepository;
        this.invitationRepository = invitationRepository1;
        this.mongoOperations=mongoOperations;
    }

    /**
     * 登录操作，主要用于查询用户和判断用户是否有另一半
     * @param users 用户帐号密码
     * @return 返回给前台的统一对象
     */
    @Override
    public ResultView Login(Users users) {
        String acPassword = users.getPasswords();
        users.setPasswords(ShareUtil.generateEncryptPass(acPassword));
        Users newUser = userMapper.selectUserByAccountAndPassword(users);
        if(newUser!=null){
            LoginDto dto = new LoginDto();
            dto.setUserId(newUser.getUserId());
            dto.setHasHalf(hasHalf(dto.getUserId()));

            //如果用户没有另一半就设置昵称用于在邀请界面显示
            if(!dto.isHasHalf()){
                //主要用户邀请对象时显示
                dto.setProfileImg(newUser.getProfileImg());
                dto.setInvitations(invitationRepository.findByInvitedId(dto.getUserId().toString()));
                dto.setNickname(newUser.getNickname());
            }
           return ResultViewUtil.success(dto);
        }else {
            return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
        }
    }


    /**
     *判断另一半是否能被邀请
     * 邀请条件：
     * 1.没有另一半
     * 2.异性
     * 3.没有被人邀请
     * @param dto 邀请和被邀请id dto
     * @return 返回给前台的统一对象
     */
    @Override
    public ResultView canInvite(InvitationIdDto dto) {
        //如果对方Id存在
        if(userIdExist(dto.getInvitedId())){
            //1.有另一半
            if(hasHalf(dto.getInvitedId())){
                return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
            }
            Users invited =userMapper.selectUserByIdOnInvite(dto.getInvitedId());
            Users invitation =userMapper.selectUserByIdOnInvite(dto.getInvitationId());
            //2.同性
            if(invited.getSex().equals(invitation.getSex())){
                return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
            }
            //3.被人邀请
            if(invitationRepository.findByInvitedId(dto.getInvitedId().toString()).size()!=0){
                return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
            }
            return ResultViewUtil.success(true);
        }
        return ResultViewUtil.error(ResultViewStatusUtil.FAILED.getCode(),ResultViewStatusUtil.FAILED.getMessage());
    }

    /**
     * 判断用户是否有另一半
     * @param userId 用户id
     * @return true有，false无
     */
    private boolean hasHalf(BigInteger userId){
        Half half=mongoOperations.findOne(query(where("user_id1").is(userId)), Half.class);
        if(half==null){
            half=mongoOperations.findOne(query(where("user_id2").is(userId)), Half.class);
            return !(half==null);
        }
        return true;
    }

    /**
     * 判断用户是否存在
     * @param userId 用户id
     * @return true存在，false不存在
     */
    private boolean userIdExist(BigInteger userId){
        return userMapper.selectUserIdById(userId)!=null;
    }
}
