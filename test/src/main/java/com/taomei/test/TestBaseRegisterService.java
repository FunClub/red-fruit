package com.taomei.test;

import com.taomei.WebApplication;
import com.taomei.service.register.serviceimpl.BaseRegisterService;
import com.taomei.service.utils.RegisterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class TestBaseRegisterService {
    @Autowired
    private BaseRegisterService service;



    @Test
    public void Test(){
        Random random = new Random();
        System.out.println(RegisterUtil.generateRandomNickName());
        assertThat(RegisterUtil.generateRandomNickName()).isNotNull();


    }
}
