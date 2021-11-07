package com.example.demo.q9;


import java.sql.*;

public class JdbcDemo {

    static String masterUrl = "jdbc:mysql://localhost/geek?serverTimezone=GMT&useSSL=no";
    static String slaveUrl = "jdbc:mysql://localhost/table_test?serverTimezone=GMT&useSSL=no";
    static String username = "root";
    static String password = "123456";

    public static void main(String[] args) throws SQLException {

        Connection masterConnection = getMasterConnection();
        PreparedStatement masterPs = masterConnection.prepareStatement("insert into test1 (id) values(?)");
        masterPs.setInt(1,1);
        masterPs.execute();

        Connection slaveConnection = getSlaveConnection();
        PreparedStatement slavePs = slaveConnection.prepareStatement("select * from test1 where id=?");
        slavePs.setInt(1,1);
        ResultSet resultSet = slavePs.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getObject(1));
        }
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
     * 获取主数据库连接
     * */
    public static Connection getMasterConnection() {

        Connection con = null;
        try {
            con = DriverManager.getConnection(masterUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 获取从数据库连接
     * */
    public static Connection getSlaveConnection() {

        Connection con = null;
        try {
            con = DriverManager.getConnection(slaveUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
