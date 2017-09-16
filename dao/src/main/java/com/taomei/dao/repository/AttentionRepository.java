package com.taomei.dao.repository;

import com.taomei.dao.entities.Attention;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttentionRepository extends MongoRepository<Attention,String> {
}
