package com.example.demo.pub;


import com.example.demo.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderPub {
    @Autowired
    private StringRedisTemplate template;

    @PostMapping("/test")
    public void test(@RequestBody Order order){
        template.convertAndSend("order.create", order.toString());
    }
}
