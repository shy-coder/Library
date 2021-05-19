package com.shy.service;

import com.shy.pojo.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserService();

    @Test
    public void selectAll() {
        List<User> list = userService.selectAll();
        System.out.println(list);
    }

    @Test
    public void deleyeUser() {
        boolean b = userService.deleteUser("1805010221");
        System.out.println(b);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("dududu");
        user.setPassword("dududu");
        user.setReader("dududu");
        user.setHeader("/header/00f110d7-05ba-4795-8647-275d0878777b.png");
        user.setDescribe("dududu");
        user.setCellphone("1312322312");
        user.setEmail("@qq.com");
        user.setSex(false);
        boolean b = userService.addUser(user);
        System.out.println(b);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId("1805010223");
        user.setUsername("lalal");
        user.setPassword("lalal");
        user.setReader("lalal");
        user.setHeader("/header/00f110d7-05ba-4795-8647-275d0878777b.png");
        user.setDescribe("lalal");
        user.setCellphone("1312322312");
        user.setEmail("5653@qq.com");
        user.setSex(false);
        boolean b = userService.updateUser(user);
        System.out.println(b);
    }
}