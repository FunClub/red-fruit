package com.taomei.service.circle.iservice;

import com.taomei.dao.dtos.base.ShowPagedArtDto;
import com.taomei.dao.dtos.circle.SelectOnePostDto;
import com.taomei.dao.dtos.circle.SelectPostConditionDto;
import com.taomei.dao.dtos.circle.ShowPostDto;
import com.taomei.dao.entities.circle.Post;


/**
 * 圈子模块服务接口
 */

public interface ICircleService {

    /**
     * 查询一个帖子
     * @param dto
     * @return
     */
    ShowPostDto selectPost(SelectOnePostDto dto);

    /**
     * 查询帖子
     * @param dto dto
     * @return 分页的帖子列表
     */
    ShowPagedArtDto<ShowPostDto> selectPosts(SelectPostConditionDto dto);
    /**
     * 添加帖子
     * @param post 帖子文档
     * @return 单个帖子dto
     */
    ShowPostDto insertPost(Post post);


}
