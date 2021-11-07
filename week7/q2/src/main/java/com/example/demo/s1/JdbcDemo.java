package com.example.demo.s1;

import java.sql.*;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class JdbcDemo {

    static String url = "jdbc:mysql://localhost/geek?serverTimezone=GMT&useSSL=no";
    static String username = "root";
    static String password = "123456";
    static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection = JdbcDemo.getConnection();

        /**
         * 1.单线程，批处理
         * 100万订单数据耗时：127135 ms
         */
        String insertPre="insert into t_order (order_id,create_time,address,user_id,remark,phone) values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(insertPre);
        connection.setAutoCommit(false);
        long start = System.currentTimeMillis();
        System.out.println("----- start ------");
        for (int i = 0; i < 1000; i++) {
            for(int j=0; j<1000; j++){
                ps.setLong(1, count.getAndIncrement());
                ps.setLong(2, System.currentTimeMillis());
                ps.setString(3,"123");
                ps.setLong(4, 1);
                ps.setString(5,"123");
                ps.setString(6,"13300000000");
                ps.addBatch();//添加到同一个批处理中
            }
            ps.executeBatch();
            System.out.println("插入"+(i+1)*1000+"条订单数据，耗时："+(System.currentTimeMillis()-start)+" ms");
        }
        connection.commit();// 提交
        System.out.println("100万订单数据插入完成，耗时："+(System.currentTimeMillis()-start)+" ms");
        connection.setAutoCommit(true);

    }


    /**
     * 加载驱动
     * */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * */
    public static Connection getConnection() {

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
