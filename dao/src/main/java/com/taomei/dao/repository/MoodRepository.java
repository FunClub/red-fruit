package com.taomei.dao.repository;

import com.taomei.dao.entities.Mood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodRepository extends MongoRepository<Mood,String> {

}
