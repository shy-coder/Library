package com.shy.dao;

import com.shy.pojo.Admin;
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
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setReader(rs.getString("reader"));
                user.setHeader(rs.getString("header"));
                user.setCellphone(rs.getString("cellphone"));
                user.setEmail(rs.getString("email"));
                user.setDescribe(rs.getString("describe"));
                user.setSex(rs.getBoolean("sex"));
            }
        }
        JDBCUtil.closeResource(connection, pstm, rs);
        return user;
    }

    public Admin selectOne(String username, String password) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Admin admin = null;
        String sql = "select * from `admin` where username = ?";
        Object[] params = {username};
        rs = JDBCUtil.execute(connection, pstm, rs, sql, params);
        while (rs.next()) {
            admin = new Admin();
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
        }
        JDBCUtil.closeResource(connection, pstm, rs);
        return admin;
    }

    public int addUser(User register) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        String sql = "INSERT IGNORE INTO `borrow_card` (username," +
                "`password`, reader) \n" +
                "SELECT ?,?,? WHERE NOT EXISTS (\n" +
                "SELECT 1 FROM borrow_card WHERE `username`=?);";
        Object[] params = {register.getUsername(), register.getPassword(), register.getReader(), register.getUsername()};
        int rs = JDBCUtil.execute(connection, pstm, sql, params);
        return rs;
    }

    public int updateOne(User user) throws SQLException {
        int rs = -1;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        StringBuilder sql = new StringBuilder("update borrow_card set reader=?, cellphone=?, email=?, sex=?, borrow_card.`describe`=? ");
        if (user.getHeader() != null) {
            sql.append(", header=? where username=?");
            Object[] params = {user.getReader(), user.getCellphone(), user.getEmail(), user.isSex(), user.getDescribe(), user.getHeader(), user.getUsername()};
            rs = JDBCUtil.execute(connection, pstm, sql.toString(), params);
        }else {
            sql.append("where username=?");
            Object[] params = {user.getReader(), user.getCellphone(), user.getEmail(), user.isSex(), user.getDescribe(), user.getUsername()};
            rs = JDBCUtil.execute(connection, pstm, sql.toString(), params);
        }
        return rs;
    }
}
