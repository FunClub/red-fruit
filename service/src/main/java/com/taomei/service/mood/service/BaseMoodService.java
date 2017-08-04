package com.taomei.service.mood.service;

import com.taomei.dao.dtos.mood.SelectMoodCondition;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.repository.MoodRepository;
import com.taomei.service.mood.iservice.IMoodService;
import com.taomei.service.share.utils.TimeUtil;
import org.bson.Document;
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

    @Autowired
    public BaseMoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
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

    @Override
    public Page<Mood> selectMoodByHalfId(SelectMoodCondition condition) {
        PageRequest pageRequest = new PageRequest(condition.getPage(),condition.getPageSize(),new Sort(Sort.Direction.DESC,"date"));
        Page<Mood> page=null;
        if(condition.isByHalf()){
            Mood mood = new Mood();
            mood.setHalfId(condition.getHalfId());
            page = moodRepository.findAll(Example.of(mood),pageRequest);
        }
        return page;
    }
}
