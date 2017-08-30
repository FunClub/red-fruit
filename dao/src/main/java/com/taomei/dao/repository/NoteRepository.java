package com.taomei.dao.repository;

import com.taomei.dao.entities.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note,String>{
}
