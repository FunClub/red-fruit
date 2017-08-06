package com.taomei.service.mood.service;

import com.taomei.dao.dtos.mood.SelectMoodConditionDto;
import com.taomei.dao.dtos.mood.ShowPagedMoodDto;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.MoodRepository;
import com.taomei.service.mood.iservice.IMoodService;
import com.taomei.service.share.utils.MoodUtils;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseMoodService implements IMoodService {
    private final MoodRepository moodRepository;
    private final UserMapper userMapper;
    @Autowired
    public BaseMoodService(MoodRepository moodRepository, UserMapper userMapper) {
        this.moodRepository = moodRepository;
        this.userMapper = userMapper;
    }

    /**
     * 插入一条心情
     * @param mood 心情文档
     * @return 插入成功与否
     */
    @Override
    public boolean insert(Mood mood) throws Exception {
        mood.setDate(TimeUtil.getSimpleTime());
        if(moodRepository.insert(mood)!=null){
            return true;
        }
        throw new Exception("插入心情失败");
    }

    /**
     * 根据条件查询心情
     * @param condition 限定条件
     * @return 分页的心情dto
     */
    @Override
    public ShowPagedMoodDto selectMood(SelectMoodConditionDto condition) {
        PageRequest pageRequest = new PageRequest(condition.getPageIndex(),condition.getPageSize(),new Sort(Sort.Direction.DESC,"date"));
        Page<Mood> page=null;
        //情侣间心情查询根据情侣id
        if(condition.isByHalf()){
            Mood mood = new Mood();
            mood.setHalfId(condition.getHalfId());
            page = moodRepository.findAll(Example.of(mood),pageRequest);
        }

        return MoodUtils.generateShowPagedMoodDto(page,userMapper);
    }
}
