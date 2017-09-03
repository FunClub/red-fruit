package com.taomei.dao.repository;

import com.taomei.dao.entities.circle.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {
}
