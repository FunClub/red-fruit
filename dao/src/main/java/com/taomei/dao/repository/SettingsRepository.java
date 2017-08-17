package com.taomei.dao.repository;

import com.taomei.dao.entities.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingsRepository extends MongoRepository<Settings,String> {
    public Settings findByUserId(String userId);
}
