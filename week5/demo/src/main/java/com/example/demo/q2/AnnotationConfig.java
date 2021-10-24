package com.example.demo.q2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 19:12
 * @需求:
 * @思路说明:
 */
@Configuration
public class AnnotationConfig {

    @Bean
    public User getUser(){
        User user = new User();
        user.setName("注解");
        user.setPhone("00000000");

        return user;
    }
}
