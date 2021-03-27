package com.shy.dao;

import com.shy.pojo.User;
import com.shy.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User selectOne(String username) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection!=null) {
            String sql = "select * from borrow_card where username=?";
            Object[] params = {username};
            rs = JDBCUtil.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setReader(rs.getString("reader"));
            }
        }
        JDBCUtil.closeResource(connection, pstm, rs);
        return user;
    }

    public int addUser(User user) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        int result = -1;
        String sql = "INSERT borrow_card (username,password,reader) VALUES (?,?,?)";
        Object[] params = {user.getUsername(), user.getPassword(), user.getReader()};
        result = JDBCUtil.execute(connection, pstm, sql, params);
        JDBCUtil.closeResource(connection, pstm, null);
        return result;
    }
}
