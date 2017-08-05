package com.taomei.dao.mapper;

import com.taomei.dao.dtos.home.HomeInfoDto;
import com.taomei.dao.dtos.mood.MoodUserInfoDto;
import com.taomei.dao.dtos.personinfo.BaseUserInfoDto;
import com.taomei.dao.dtos.personinfo.UpdateProfileDto;
import com.taomei.dao.entities.Users;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserMapper {

    /**
     * 查询心情发布人的信息
     * @param userId 用户Id
     * @return
     */
    MoodUserInfoDto selectMoodUserInfo(String userId);

    /**
     * 查询用户的主页信息
     * @param userId 用户id
     * @return
     */
    HomeInfoDto selectHomeInfo(String userId);
    /**
     * 更新用户头像
     * @param dto 用户头像
     * @return
     */
    int updateUserProfile(UpdateProfileDto dto);
    int updateUserBaseInfoById(BaseUserInfoDto dto);
    /**
     * 根据用户id查询用户的所有基本信息
     * @param bigInteger 用户信息
     * @return
     */
    BaseUserInfoDto selectUserBaseInfoById(String bigInteger);
    /**
     * 通过id查询用户
     * @param userId 用户id
     * @return
     */
    BigInteger selectUserIdById(String userId);
    /**
     * 查询被邀请人或者邀请人的信息(性别)
     * @param userId 用户
     * @returnId
     */
    Users selectUserByIdOnInvite(String userId);

    /**
     * @param users 用户对象，只有帐号密码
     * @return 查询出来的用户信息
     */
    Users selectUserByAccountAndPassword(Users users);
    /**
     *根据账号来查询账号，用于判断该账号是否被注册
     * @param account 用户待注册的账号
     * @return 查询出来的account
     */
    String selectAccountByAccount(String account);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    int insert(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated
     */
    int insertSelective(Users record);
}