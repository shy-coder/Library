package com.shy.service;

import com.shy.dao.BookMapper;
import com.shy.dao.UserDao;
import com.shy.dao.UserMapper;
import com.shy.pojo.Admin;
import com.shy.pojo.User;
import com.shy.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    public List<User> selectAll() {
        List<User> list = userMapper.selectAll();
        return list;
    }

    public boolean deleteUser(String id) {
        int i = userMapper.deleteUser(id);
        if (i>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean addUser(User user) {
        int i = userMapper.addUser(user);
        if (i>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean updateUser(User user) {
        int i = userMapper.updateUser(user);
        if (i>0) {
            return true;
        }else {
            return false;
        }
    }

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
                session.setAttribute("userId",user.getId());
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

    public User getUserInfo(String username) {
        User user = null;
        try {
            user =  userDao.selectOne(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String uploadUserInfo(User user, HttpSession session) {
        int result = 0;
        try {
            result = userDao.updateOne(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result > 0) {
            User userInfo = getUserInfo(user.getUsername());
            session.setAttribute("user", userInfo);
            return "更新成功";
        }
        return "更新失败";
    }
}
