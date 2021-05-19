package com.shy.servlet.admin;

import com.shy.pojo.User;
import com.shy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserInfoServlet" ,urlPatterns = "/user/getAllUser")
public class SearchUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = new UserService();
        final List<User> users = userService.selectAll();
        req.getSession().setAttribute("users",users);
    }
}
