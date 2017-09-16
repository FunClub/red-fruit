package com.taomei.service.share.service;

import com.taomei.dao.dtos.share.ShowCardUserDto;
import com.taomei.dao.dtos.share.UserNPInfoDto;
import com.taomei.dao.entities.Attention;
import com.taomei.dao.entities.Half;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.AttentionRepository;
import com.taomei.dao.repository.HalfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 * 共享服务
 */
@Service
public class ShareService {
    private final HalfRepository halfRepository;
    private final UserMapper userMapper;
    private final MongoOperations mongoOperations;
    private final AttentionRepository attentionRepository;
    @Autowired
    public ShareService(HalfRepository halfRepository, UserMapper userMapper, MongoOperations mongoOperations, AttentionRepository attentionRepository) {
        this.halfRepository = halfRepository;
        this.userMapper = userMapper;
        this.mongoOperations = mongoOperations;
        this.attentionRepository = attentionRepository;
    }

    /**
     * 添加关注
     * @param attention 关注文档
     * @return 成功与否
     */
    public boolean addAttention(Attention attention){
        attentionRepository.insert(attention);
        return  true;
    }
    /**
     * 查询名片
     *
     * @param userId 用户id
     * @return 名片上的用户信息dto
     */
    public ShowCardUserDto selectCardUser(String userId) {
        ShowCardUserDto cardUserDto = userMapper.selectCardUserDto(userId);
        if( cardUserDto.getSex().equals("1")){
            cardUserDto.setSex("男");
        }else {
            cardUserDto.setSex("女");
        }

        String halfUserId;
        Half half=halfRepository.findByUserId1(userId);
        if(half!=null){
            halfUserId = half.getUserId2();
        }else{
            half=halfRepository.findByUserId2(userId);
            halfUserId = half.getUserId1();
        }
        UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(halfUserId);
        cardUserDto.setHalfNickname(npInfoDto.getNickname());
        return cardUserDto;
    }
}
