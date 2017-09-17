package com.taomei.service.share.service;

import com.taomei.dao.dtos.share.card.SelectCardDto;
import com.taomei.dao.dtos.share.card.ShowCardUserDto;
import com.taomei.dao.dtos.share.user.AttentionUserDto;
import com.taomei.dao.dtos.share.user.UserNPInfoDto;
import com.taomei.dao.entities.Attention;
import com.taomei.dao.entities.Half;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.AttentionRepository;
import com.taomei.dao.repository.HalfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
     * 查询被关注人信息
     * @param userId 关注人id
     * @return  被关注人信息集合
     */
    public List<AttentionUserDto> selectAttentionUsers(String userId){
        List<Attention> attentions = attentionRepository.findByUserId(userId);
        List<AttentionUserDto> dtos = new ArrayList<>();
        AttentionUserDto attentionUserDto;
        for (Attention attention:attentions){
            attentionUserDto=userMapper.selectAttentionUserDto(attention.getAttentionUserId());
            if( attentionUserDto.getSex().equals("1")){
                attentionUserDto.setSex("男");
            }else {
                attentionUserDto.setSex("女");
            }
            dtos.add(attentionUserDto);
        }
        return dtos;
    }
    /**
     * 取消关注
     * @param dto 查询名片的dto
     * @return 成功与否
     */
    public boolean deleteAttention(SelectCardDto dto){
        String cardUserId = dto.getCardUserId();
        String userId = dto.getUserId();
        Query query = Query.query(where("userId").is(userId).and("attentionUserId").is(cardUserId));
        return mongoOperations.remove(query,Attention.class,"attention").getN()>0;
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
     * @param dto 查询名片的dto
     * @return 名片上的用户信息dto
     */
    public ShowCardUserDto selectCardUser(SelectCardDto dto) {
        String cardUserId = dto.getCardUserId();
        String userId = dto.getUserId();
        ShowCardUserDto cardUserDto = userMapper.selectCardUserDto(cardUserId);
        if( cardUserDto.getSex().equals("1")){
            cardUserDto.setSex("男");
        }else {
            cardUserDto.setSex("女");
        }
        String halfUserId;
        Half half=halfRepository.findByUserId1(cardUserId);
        if(half!=null){
            halfUserId = half.getUserId2();
        }else{
            half=halfRepository.findByUserId2(cardUserId);
            halfUserId = half.getUserId1();
        }
        UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(halfUserId);
        cardUserDto.setHalfNickname(npInfoDto.getNickname());

        /*判断是否已经关注*/
        Attention attention = new Attention();
        attention.setUserId(userId);
        attention.setAttentionUserId(cardUserId);
        attention=attentionRepository.findOne(Example.of(attention));
        cardUserDto.setAttentionAble(attention==null);
        return cardUserDto;
    }
}
