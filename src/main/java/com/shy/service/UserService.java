package com.shy.service;

import com.shy.dao.UserDao;
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

    public String addUser(User user, HttpSession session) {
        String result;
        int rs = -1;
        try {
            rs = userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs == 1) {
            result = "注册成功";
        } else {
            result = "注册失败";
            session.setAttribute("message", "注册失败");
        }
        return result;
    }
}
