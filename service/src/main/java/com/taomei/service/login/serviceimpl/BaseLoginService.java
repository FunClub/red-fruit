package com.taomei.service.login.serviceimpl;

import com.taomei.dao.dtos.login.InvitationIdDto;
import com.taomei.dao.dtos.login.LoginDto;
import com.taomei.dao.entities.Half;
import com.taomei.dao.entities.ResultView;
import com.taomei.dao.entities.Users;
import com.taomei.dao.entities.Invitation;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.HalfRepository;
import com.taomei.dao.repository.InvitationRepository;
import com.taomei.service.login.iservice.ILoginService;

import com.taomei.service.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登陆服务接口的基本实现
 */
@Service
public class BaseLoginService implements ILoginService {

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
     * @return 登录dto
     */
    @Override
    public LoginDto Login(Users users) throws Exception {
        String acPassword = users.getPasswords();
        users.setPasswords(ShareUtil.generateEncryptPass(acPassword));
        Users newUser = userMapper.selectUserByAccountAndPassword(users);
        if(newUser!=null){
            LoginDto dto = new LoginDto();
            dto.setUserId(newUser.getUserId().toString());
            Half half = getHalf(dto.getUserId());
            dto.setHalf(half);
            dto.setHasHalf(half!=null);
            //如果用户没有另一半就设置昵称用于在邀请界面显示
            if(!dto.isHasHalf()){
                //主要用户邀请对象时显示
                dto.setProfileImg(newUser.getProfileImg());
                dto.setNickname(newUser.getNickname());
            }
           return dto;
        }else {
            throw new Exception("登录失败");
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
    public boolean canInvite(InvitationIdDto dto) throws Exception {
        //如果对方Id存在
        if(userIdExist(dto.getInvitedId())){
            //1.有另一半
            if(hasHalf(dto.getInvitedId())){
                throw new Exception("无法邀请");
            }
            Users invited =userMapper.selectUserByIdOnInvite(dto.getInvitedId());
            Users invitation =userMapper.selectUserByIdOnInvite(dto.getInvitationId());
            //2.同性
            if(invited.getSex().equals(invitation.getSex())){
                throw new Exception("无法邀请");
            }
            //3.被人邀请
            if(invitationRepository.findByInvitedId(dto.getInvitedId()).size()!=0){
                throw new Exception("无法邀请");
            }
            return true;
        }
        throw new Exception("无法邀请");
    }

    /**
     * 查询邀请信息
     * @param userId 用户Id
     * @return 邀请信息
     */
    public List<Invitation> selectInvitation(String userId){
        return this.invitationRepository.findByInvitedId(userId);
    }
    /**
     * 判断用户是否有另一半
     * @param userId 用户id
     * @return true有，false无
     */
    public boolean hasHalf(String userId){
        return getHalf(userId)!=null;
    }

    /**
     * 获取用户另一半对象
     * @param userId 用户id
     * @return 另一半对象
     */
    public Half getHalf(String userId){
        Half half=halfRepository.findByUserId1(userId);
        if(half==null){
            half=halfRepository.findByUserId2(userId);
        }
        return half;
    }
    /**
     * 判断用户是否存在
     * @param userId 用户id
     * @return true存在，false不存在
     */
    private boolean userIdExist(String userId){
        return userMapper.selectUserIdById(userId)!=null;
    }
}
