package com.example.demo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布订阅模式
 */
public class TopicTest {
    public static void main(String[] args) {

        // 连接工厂
        ConnectionFactory factory;
        // 连接实例
        Connection connection = null;
        // 会话
        Session session = null;
        // 定义Destination
        Destination destination;
        // 生产者
        MessageProducer messageProducer;
        // 订阅者1
        MessageConsumer messageConsumer1;
        // 订阅者2
        MessageConsumer messageConsumer2;

        try{
            factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            // 获取连接实例
            connection = factory.createConnection();
            // 启动
            connection.start();
            // 创建收发线程实例
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建topic
            destination = session.createTopic("topic");

            // 创建订阅者1
            messageConsumer1 = session.createConsumer(destination);
            // 绑定监听器
            messageConsumer1.setMessageListener(message -> System.out.println( "订阅者1：receive from " + destination.toString() + ": " + message));

            // 创建订阅者2
            messageConsumer2 = session.createConsumer(destination);
            // 绑定监听器
            messageConsumer2.setMessageListener(message -> System.out.println( "订阅者2：receive from " + destination.toString() + ": " + message));


            // 创建生产者
            messageProducer = session.createProducer(destination);
            // 创建消息实体
            TextMessage message = session.createTextMessage("hello, topic");
            // 发送消息
            messageProducer.send(message);

            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
