package com.example.demo.sub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class OrderSub implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        System.out.println("-----处理：" + body);

    }
}
