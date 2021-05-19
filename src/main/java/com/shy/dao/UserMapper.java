package com.shy.dao;

import com.shy.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    int deleteUser(String id);

    int addUser(User user);

    int updateUser(User user);

}
