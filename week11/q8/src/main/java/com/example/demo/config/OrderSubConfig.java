package com.example.demo.config;


import com.example.demo.sub.OrderSub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import javax.annotation.Resource;

@Configuration
public class OrderSubConfig {

    @Resource
    private OrderSub orderSub;

    @Bean
    RedisMessageListenerContainer container(final RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListenerAdapter(orderSub), new ChannelTopic("order.create"));
        return container;
    }
}
