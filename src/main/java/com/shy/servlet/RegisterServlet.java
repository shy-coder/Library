package com.shy.servlet;

import com.shy.pojo.User;
import com.shy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String reader = req.getParameter("reader");
        User user = new User(username, password, reader);
        String result = userService.addUser(user, req.getSession());
        if ("注册成功".equals(result)) {
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        } else {
            req.getRequestDispatcher("/register.jsp?message=" + result).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
