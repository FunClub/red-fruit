package com.taomei.test;

import com.taomei.WebApplication;
import com.taomei.dao.entities.Mood;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.entities.discussion.SubDiscussion;
import com.taomei.dao.repository.MoodRepository;
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
    MongoOperations mongoOperations;


    @Test
    public void insertAll() throws IOException {
        Mood mood = new Mood();
        mood.setUserId("904620272@qq.com");
        mood.setContent("心情内容");

        /*子评论集合1*/
        List<SubDiscussion> subDiscussions1 = new ArrayList<>();
        SubDiscussion subDiscussion1 = new SubDiscussion();subDiscussion1.setUserId("1");subDiscussion1.setContent("子评论1");
        SubDiscussion subDiscussion2 = new SubDiscussion();subDiscussion2.setUserId("2");subDiscussion2.setContent("子评论2");
        subDiscussions1.add(subDiscussion1);subDiscussions1.add(subDiscussion2);

        /*子评论集合2*/
        List<SubDiscussion> subDiscussions2 = new ArrayList<>();
        SubDiscussion subDiscussion3 = new SubDiscussion();subDiscussion3.setUserId("3");subDiscussion3.setContent("子评论3");
        SubDiscussion subDiscussion4 = new SubDiscussion();subDiscussion4.setUserId("4");subDiscussion4.setContent("子评论4");
        subDiscussions2.add(subDiscussion3);subDiscussions2.add(subDiscussion4);


        /*父级评论1*/
        ParentDiscussion parentDiscussion1 = new ParentDiscussion();parentDiscussion1.setUserId("5");parentDiscussion1.setContent("父评论1");
        parentDiscussion1.setSubDiscussions(subDiscussions1);

         /*父级评论2*/
        ParentDiscussion parentDiscussion2 = new ParentDiscussion();parentDiscussion2.setUserId("6");parentDiscussion2.setContent("父评论2");
        parentDiscussion2.setSubDiscussions(subDiscussions2);

        List<ParentDiscussion> parentDiscussions = new ArrayList<>();
        parentDiscussions.add(parentDiscussion1);parentDiscussions.add(parentDiscussion2);

        mood.setParentDiscussions(parentDiscussions);

        moodRepository.save(mood);

    }

    @Test
    public void deleteParent(){
        String moodId="5983fde40cfefa1ab8a5dc91";
        String parentId="3";
        Query query = Query.query(where("_id").is(moodId));
        Document document=new Document("userId",parentId);
        Update update = new Update();
        update.pull("parentDiscussions.subDiscussions",document);
        int count=mongoOperations.updateFirst(query,update,Mood.class).getN();
        assertThat(count).isEqualTo(1);
    }

}
