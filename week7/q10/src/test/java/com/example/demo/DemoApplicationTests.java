package com.example.demo;

import com.example.demo.q10.TestMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    TestMapper testMapper;

    @Test
    void contextLoads() {
        System.out.println("-------");
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue("user");

        Integer uid= testMapper.selectUserById(1);
//        System.out.println(uid);
        hintManager.close();

        hintManager.setDatabaseShardingValue("order");
//        Integer oid= testMapper.selectOrderById(1);
//        System.out.println(oid);
//        hintManager.close();
    }

}
