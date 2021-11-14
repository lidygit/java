package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

@ImportResource(locations = {"classpath:XA.xml"})
@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    @Test
    void contextLoads() {
        List<Order> orders = orderMapper.selectOrderList();
        for (Order order:orders) {
            System.out.println(order.toString());
        }

        System.out.println("---------------total:"+orders.size());
    }

    @Test
    void test0() {
        List<Order> orders = orderMapper.selectById(421L);
        for (Order order:orders) {
            System.out.println(order.toString());
        }

        System.out.println("---------------size:"+orders.size());
    }

    @Test
    void test1() {
        int i = orderMapper.update(82L, "北京");

        Assert.assertEquals(i, 1);
    }

    @Test
    void test2() {
        int i = orderMapper.delete(464L);
        Assert.assertEquals(i, 1);
    }
    @Test
    void test3() throws InterruptedException {
        long id;
        for (int i = 100; i < 500; i++) {
            id=i;
            orderMapper.insert(id, System.currentTimeMillis(), "address", id, "测试呀", "18800000000");
            Thread.sleep(100);
        }
    }

    @Test
    void test4() throws InterruptedException {
     orderService.insert();
    }
}
