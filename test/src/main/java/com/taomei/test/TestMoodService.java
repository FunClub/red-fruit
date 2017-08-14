package com.taomei.test;

import com.taomei.WebApplication;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.entities.discussion.SubDiscussion;
import com.taomei.dao.repository.MoodRepository;
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
    ImageService imageService;


    @Test
    public void name() throws Exception {
        this.imageService.deleteImg("profile/defaultMeImg.png");
    }
}
