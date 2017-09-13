package com.taomei.dao.repository;

import com.taomei.dao.entities.chat.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat,String> {
    public List<Chat> findByHalfId(String halfId);
}
