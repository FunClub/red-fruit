package com.taomei.test;

import com.taomei.WebApplication;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.entities.discussion.SubDiscussion;
import com.taomei.dao.repository.MoodRepository;
import com.taomei.dao.repository.PhotoRepository;
import com.taomei.service.share.ImageService;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class TestMoodService {
    @Autowired
    private MoodRepository moodRepository;

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    MongoOperations mongoOperations;

    @Test
    public void name() throws Exception {
            Query query =Query.query(where("_class").is("com.taomei.dao.entities.album.Photo"));
            Update update =Update.update("userId","201321");
            for(int i=0;i<80;i++){
                mongoOperations.updateFirst(query,update,"photo");
            }

    }
}
