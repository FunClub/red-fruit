package com.taomei.service.discussion.service;

import com.taomei.dao.dtos.discussion.SelectDiscussionConditionDto;
import com.taomei.dao.dtos.discussion.ShowPagedDiscussionDto;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.service.discussion.iservice.IDiscussionService;
import com.taomei.service.share.statics.SortBy;
import com.taomei.service.share.utils.DiscussionUtil;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BaseDiscussionService implements IDiscussionService{

    private final DiscussionRepository discussionRepository;
    private final UserMapper userMapper;
    @Autowired
    public BaseDiscussionService(DiscussionRepository discussionRepository, UserMapper userMapper) {
        this.discussionRepository = discussionRepository;
        this.userMapper = userMapper;
    }

    /**
     * 插入父级评论
     * @param discussion 评论
     * @return 成功与否
     */
    @Override
    public boolean insertParentDiscussion(ParentDiscussion discussion) throws Exception {
        discussion.setDate(TimeUtil.getSimpleTime());
       ParentDiscussion parentDiscussion= discussionRepository.insert(discussion);
       if(parentDiscussion!=null){
           return true;
       }
       throw new Exception("插入评论失败");
    }

    @Override
    public ShowPagedDiscussionDto selectDiscussion(SelectDiscussionConditionDto dto) {
        Sort sortBy=null;
        if(dto.getSortBy().equals(SortBy.LATEST.getSort())){
            sortBy=new Sort(Sort.Direction.DESC,"date");
        }else if (dto.getSortBy().equals(SortBy.HOT.getSort())){
            sortBy=new Sort(Sort.Direction.DESC,"subDiscussionsLength");
        }
        ParentDiscussion parentDiscussion = new ParentDiscussion();
        parentDiscussion.setArtId(dto.getArtId());
        PageRequest pageRequest= new PageRequest(dto.getPageIndex(),dto.getPageSize(),sortBy);
        Page<ParentDiscussion> discussionPage=discussionRepository.findAll(Example.of(parentDiscussion),pageRequest);
        return DiscussionUtil.showPagedDiscussionDto(discussionPage, userMapper);
    }
}
