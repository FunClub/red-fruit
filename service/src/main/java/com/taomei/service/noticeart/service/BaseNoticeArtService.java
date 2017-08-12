package com.taomei.service.noticeart.service;

import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.noticeart.SelectNoticeArtConditionDto;
import com.taomei.dao.dtos.noticeart.ShowNoticeArtDto;
import com.taomei.dao.dtos.noticeart.ShowPagedNoticeArtDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.MoodRepository;
import com.taomei.dao.repository.NoticeArtRepository;

import com.taomei.service.noticeart.iservice.INoticeArtService;
import com.taomei.service.share.enums.ArtType;
import com.taomei.service.share.enums.NoticeArtType;
import com.taomei.service.share.utils.DiscussionUtil;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态通知的基本实现
 */
@Service
public class BaseNoticeArtService implements INoticeArtService {
    private final NoticeArtRepository artRepository;
    private final MongoOperations mongoOperations;
    private final MoodRepository moodRepository;
    private final UserMapper userMapper;
    @Autowired
    public BaseNoticeArtService(NoticeArtRepository artRepository, MongoOperations mongoOperations, MoodRepository moodRepository, UserMapper userMapper) {
        this.artRepository = artRepository;
        this.mongoOperations = mongoOperations;
        this.moodRepository = moodRepository;
        this.userMapper = userMapper;
    }

    /**
     * 查询用户动态信息
     * @param dto 用户id
     * @return 分页的动态信息
     */
    @Override
    public ShowPagedNoticeArtDto selectNoticeArt(SelectNoticeArtConditionDto dto) {
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(),dto.getPageSize());
        NoticeArt noticeArtExample = new NoticeArt();
        noticeArtExample.setNoticeArtUserId(dto.getUserId());
        Page<NoticeArt> page=artRepository.findAll(Example.of(noticeArtExample),pageRequest);
        ShowPagedNoticeArtDto pageDto = new ShowPagedNoticeArtDto();
        pageDto.setTotalElements(page.getTotalElements());
        List<ShowNoticeArtDto> noticeArtDtos = new ArrayList<>();
        pageDto.setContent(noticeArtDtos);
        ShowNoticeArtDto noticeArtDto=null;
        for(NoticeArt noticeArt:page){
            noticeArtDto= new ShowNoticeArtDto();
            //设置基本信息
            noticeArtDto.setGenerateUserId(noticeArt.getGenerateUserId());
            noticeArtDto.setArtType(noticeArt.getArtType());
            noticeArtDto.setArtId(noticeArt.getArtId());
            noticeArtDto.setNoticeArtType(noticeArt.getNoticeArtType());
            UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(noticeArtDto.getGenerateUserId());
            noticeArtDto.setGenerateNickname(npInfoDto.getNickname());
            noticeArtDto.setGenerateProfileImg(npInfoDto.getProfileImg());
            noticeArtDto.setShortDate(TimeUtil.generateShortDate(noticeArt.getDate()));
            noticeArtDto.setSortDate(TimeUtil.calculateSortDate(noticeArt.getDate()));
            noticeArtDto.setArtContent(noticeArt.getArtContent());
            noticeArtDto.setOriginal(noticeArt.getOriginal());
            noticeArtDto.setFirstArtImg(noticeArt.getFirstArtImg());
            //填充动态人的昵称,和id
            //如果动态不是原创
            if(!noticeArt.getOriginal()){
                String originalUserId = noticeArt.getOriginalUserId();
                UserNPInfoDto originalUserDto = userMapper.selectUserNPInfo(originalUserId);
                noticeArtDto.setArtNickname(originalUserDto.getNickname());

                noticeArtDto.setOriginalArtId(noticeArt.getOriginalArtId());
                noticeArtDto.setOriginalUserId(noticeArt.getOriginalUserId());
            }else{
                String noticeArtUserId = noticeArt.getNoticeArtUserId();
                UserNPInfoDto artUserDto = userMapper.selectUserNPInfo(noticeArtUserId);
                noticeArtDto.setArtNickname(artUserDto.getNickname());
            }
            noticeArtDtos.add(noticeArtDto);
            //生成评论
        }
        return pageDto;
    }

    /**
     * 插入动态通知
     * @param noticeArt 通知动态文档
     * @return 成功与否
     */
    @Override
    public boolean insertNoticeArt(NoticeArt noticeArt) {

        return artRepository.insert(noticeArt)!=null;
    }
}
