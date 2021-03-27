package com.shy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    static {
        init();
    }

    private static String driver;
    private static String url;
    private static String username;
    private static String passwrod;

    /**
     * 初始化参数连接，从配置文件里获得
     */
    public static void init() {
        Properties params = new Properties();
        String configFile = "db.properties";
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream(configFile);
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = params.getProperty("driver");
        url = params.getProperty("url");
        username = params.getProperty("username");
        passwrod = params.getProperty("password");
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, passwrod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询操作
     * @param connection
     * @param pstm
     * @param rs
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static ResultSet execute(Connection connection, PreparedStatement pstm, ResultSet rs, String sql, Object[] params) throws SQLException {
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i+1, params[i]);
        }
        rs = pstm.executeQuery();
        return rs;
    }

    /**
     * 增、删、改操作
     * @param connection
     * @param pstm
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int execute(Connection connection, PreparedStatement pstm, String sql, Object[] params) throws SQLException {
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i+1, params[i]);
        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

    /**
     * 释放资源
     * @param connection
     * @param pstm
     * @param rs
     * @return
     */
    public static boolean closeResource(Connection connection, PreparedStatement pstm, ResultSet rs) {
        boolean flag = true;
        if (rs != null) {
            try {
                rs.close();
                rs = null;  //GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
                pstm = null;  //GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;  //GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

}
