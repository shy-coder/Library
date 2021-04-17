package com.shy.service;

import com.shy.dao.UserDao;
import com.shy.pojo.Admin;
import com.shy.pojo.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UserService {

    private UserDao userDao = new UserDao();

    public String login(String username, String password, HttpSession session) {
        User user = null;
        try {
            user = userDao.selectOne(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            return "用户不存在";
        } else {
            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                session.setAttribute("isLogin", true);
                return "1";
            } else {
                return "密码错误";
            }
        }
    }

    public String adminLogin(String username, String password, HttpSession session) {
        Admin admin = null;
        try {
            admin = userDao.selectOne(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (admin == null) {
            return "用户不存在";
        } else {
            if (password.equals(admin.getPassword())) {
                session.setAttribute("admin", admin);
                session.setAttribute("isLogin", true);
                return "1";
            } else {
                return "密码错误";
            }
        }
    }

    public String register(User register) {
        int result = 0;
        try {
            result = userDao.addUser(register);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return "注册成功";
        } else {
            return "用户已存在";
        }
    }
}
