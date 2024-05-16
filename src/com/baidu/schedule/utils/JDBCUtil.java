package com.baidu.schedule.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static final DataSource dataSource;

    //初始化连接池
    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //向外提供连接池的方法
    public static DataSource getDataSource() {
        return dataSource;
    }

    //向外提供连接的方法
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (null == connection) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = threadLocal.get();
        if (null != connection) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.remove();
        }
    }
}
