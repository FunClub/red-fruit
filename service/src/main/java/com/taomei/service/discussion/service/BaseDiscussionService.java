package com.taomei.service.discussion.service;

import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.service.discussion.iservice.IDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseDiscussionService implements IDiscussionService{

    private final DiscussionRepository discussionRepository;

    @Autowired
    public BaseDiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    /**
     * 插入父级评论
     * @param discussion 评论
     * @return 成功与否
     */
    @Override
    public boolean insertParentDiscussion(ParentDiscussion discussion) {
        return discussionRepository.insert(discussion)!=null;
    }
}
