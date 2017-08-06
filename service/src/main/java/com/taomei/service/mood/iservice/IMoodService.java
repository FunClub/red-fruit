package com.taomei.service.mood.iservice;

import com.taomei.dao.dtos.mood.SelectMoodConditionDto;
import com.taomei.dao.dtos.mood.ShowPagedMoodDto;
import com.taomei.dao.entities.Mood;
import org.springframework.data.domain.Page;

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
     * @return 分页的心情dto
     */
    ShowPagedMoodDto selectMood(SelectMoodConditionDto condition);
}
