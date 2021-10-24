package com.example.demo;

import com.example.demo.q2.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	// 通过注解注入bean
	@Resource
	private User user;


	// 通过xml注入bean
	@Resource
	private User userAAA;

	@Test
	void annotation() {
		System.out.println(user.toString());
	}


	@Test
	void xml() {
		System.out.println(userAAA.toString());
	}

}
