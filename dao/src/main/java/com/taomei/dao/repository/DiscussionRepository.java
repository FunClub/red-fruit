package com.taomei.dao.repository;

import com.taomei.dao.entities.discussion.ParentDiscussion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 评论仓库
 */
public interface DiscussionRepository extends MongoRepository<ParentDiscussion,String> {
}
