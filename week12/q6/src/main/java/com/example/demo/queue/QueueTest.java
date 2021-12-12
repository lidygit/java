package com.example.demo.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点模式
 */
public class QueueTest {
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
        // 消费者
        MessageConsumer messageConsumer;

        try{
            factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            // 获取连接实例
            connection = factory.createConnection();
            // 启动
            connection.start();
            // 创建收发线程实例
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建队列
            destination = session.createQueue("queue");

            // 创建消费者
            messageConsumer = session.createConsumer(destination);
            // 监听
            messageConsumer.setMessageListener(message -> System.out.println( "receive from " + destination.toString() + ": " + message));


            // 创建生产者
            messageProducer = session.createProducer(destination);
            // 创建消息实体
            TextMessage message = session.createTextMessage("hello, queue");
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
