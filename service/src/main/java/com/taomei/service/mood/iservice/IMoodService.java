package com.taomei.service.mood.iservice;

import com.taomei.dao.dtos.mood.SelectMoodCondition;
import com.taomei.dao.entities.Half;
import com.taomei.dao.entities.Mood;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 心情服务接口
 */
public interface IMoodService {

    /**
     * 插入一条心情
     * @param mood 心情文档
     * @return 插入成功与否
     */
    boolean insert(Mood mood) throws Exception;

    /**
     * 通过条件查询心情
     * @param condition 限定条件
     * @return 心情集合
     */
    Page<Mood> selectMoodByHalfId(SelectMoodCondition condition);
}
