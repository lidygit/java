package com.example.demo.q10;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/24 16:00
 * @需求:
 * @思路说明:
 */
public class JDBCDemo {
    static String url = "jdbc:mysql://localhost/table_test?serverTimezone=GMT&useSSL=no";
    static String username = "root";
    static String password = "123456";

    public static void main(String[] args) throws SQLException {
        /** 1）使用 JDBC 原生接口，实现数据库的增删改查操作。 */
        Connection connection = JDBCDemo.getConnection();
        Statement statement = connection.createStatement();
        String select="select * from t_user";
        String insert="insert into t_user (id,username,password,email,realName,gender,age,headImg)" +
                " values(null,'haha','123',null,null,null,18,null)";
        String update="update t_user set username='admin' where id=13;";
        String delete="delete from t_user where id=14";
        ResultSet rs=statement.executeQuery(select);
        printResultSet(rs);
        statement.execute(insert);
        statement.execute(update);
        statement.execute(delete);

        /** 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。 */
        String selectPre="select * from t_user where id=?";
        String insertPre="insert into t_user (id,username,password) values(?,?,?)";
        String updatePre="update t_user set username=? where id=?;";
        String deletePre="delete from t_user where id=?";
        PreparedStatement ps = connection.prepareStatement(selectPre);
        ps.setInt(1,15);
        ResultSet resultSet = ps.executeQuery();
        printResultSet(resultSet);

        // 事务+批处理
        connection.setAutoCommit(false);
        ps=connection.prepareStatement(insertPre);

        for(int i=0; i<10; i++){
            ps.setString(1, null);
            ps.setString(2, "zhangsan");
            ps.setString(3,"123");
            if(i==8){
                ps.setString(1, "12");
            }
            ps.addBatch();//添加到同一个批处理中
        }
        ps.executeBatch();

        connection.commit();// 提交
        connection.setAutoCommit(true);

        ps=connection.prepareStatement(updatePre);
        for(int i=0; i<10; i++) {
            ps.setString(1, "xixi");
            ps.setInt(2, i+20);
            ps.addBatch();
        }
        ps.executeBatch();

        ps=connection.prepareStatement(deletePre);
        ps.setInt(1, 18);
        ps.execute();
        
        /** 3）配置 Hikari 连接池，改进上述操作 */
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        Connection conn = dataSource.getConnection();

        // 事务+批处理
        conn.setAutoCommit(false);

        PreparedStatement psHi = conn.prepareStatement(insertPre);
        for(int i=0; i<10; i++){
            psHi.setString(1, null);
            psHi.setString(2, "hiraki");
            psHi.setString(3,"hiraki");
            if(i==8){
                psHi.setString(1, "12");
            }
            psHi.addBatch();//添加到同一个批处理中
        }
        psHi.executeBatch();
        conn.commit();

    }

    /**
     * 加载驱动
     * */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
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

    /**
     * 获取statement对象，操作数据库，处理返回结果
     * */
    public static void process() {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            ps = con.prepareStatement(sql);
            if (ps.execute()) {
                rs = ps.getResultSet();
            } else {
                int i = ps.getUpdateCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
    }

    /**
     * 处理返回结果集
     * */
    public static void printResultSet(ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            StringBuffer b = new StringBuffer();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    b.append(meta.getColumnName(i) + "=");
                    b.append(rs.getString(i) + " ");
                }
                b.append("\r\n");
            }
            System.out.print(b.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     * */
    public static void close(ResultSet rs, Statement stm, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stm != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
