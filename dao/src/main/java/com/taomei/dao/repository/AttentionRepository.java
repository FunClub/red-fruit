package com.taomei.dao.repository;

import com.taomei.dao.entities.Attention;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttentionRepository extends MongoRepository<Attention,String> {
    public List<Attention> findByUserId(String userId);
}
