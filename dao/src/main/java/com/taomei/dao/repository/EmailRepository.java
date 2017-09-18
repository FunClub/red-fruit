package com.taomei.dao.repository;

import com.taomei.dao.entities.email.Email;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailRepository extends MongoRepository<Email,String> {
    List<Email> findByFromUserIdOrReceiveUserIdOrderByDateDesc(String fromUserId, String receiveUserId);
}
