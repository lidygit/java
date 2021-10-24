package com.example.demo;

import com.example.demo.q2.UserConfig;
import com.example.demo.q2.UserXML;
import com.example.demo.q2.UserAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    // 通过xml注入bean
    @Resource
    private UserXML userXML;

	// 通过注解注入bean
	@Resource
	private UserAnnotation userAnnotation;

	// 通过config
	@Resource
    private UserConfig userConfig;

	@Test
	void config() {
		System.out.println(userConfig.toString());
	}


	@Test
	void xml() {
		System.out.println(userXML.toString());
	}

	@Test
    void annotation(){
        System.out.println(userAnnotation.toString());
    }
}
