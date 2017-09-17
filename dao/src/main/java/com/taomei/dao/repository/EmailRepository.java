package com.taomei.dao.repository;

import com.taomei.dao.entities.email.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email,String> {
}
