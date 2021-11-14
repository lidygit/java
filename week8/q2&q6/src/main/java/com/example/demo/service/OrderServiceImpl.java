package com.example.demo.service;


import com.example.demo.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    JtaTransactionManager springJTATransanctionManager;

    /** 不知道为什么，声明式事务不生效， 编程式事务rollback调用了，但数据没有回滚 */
//    @Transactional(value = "springJTATransanctionManager",rollbackFor = Exception.class)
//    @ShardingTransactionType(TransactionType.XA)
    public void insert() throws InterruptedException {
        UserTransaction userTransaction = springJTATransanctionManager.getUserTransaction();
        try {
            userTransaction.begin();
            long id;
            for (int i = 2300; i < 2400; i++) {
                id = i;
                orderMapper.insert(id, System.currentTimeMillis(), "address", id, "测试呀", "18800000000");
                if (id == 2350) {
                    int k = 1 / 0;
                }
                Thread.sleep(10);
            }
            userTransaction.commit();
        }catch (Exception e){
            try {
                userTransaction.rollback();
                System.out.println("----------rollback---------"+e.getMessage());
            } catch (SystemException e1) {
                System.out.println(e.getMessage());
            }

        }
    }
}
