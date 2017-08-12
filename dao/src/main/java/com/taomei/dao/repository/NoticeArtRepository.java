package com.taomei.dao.repository;

import com.taomei.dao.entities.NoticeArt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeArtRepository extends MongoRepository<NoticeArt,String> {
}
