package com.shy.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shy.pojo.User;
import com.shy.service.UserService;
import lombok.val;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AddUser",urlPatterns = "/user/addUser")
public class AddUserServlet extends HttpServlet {
    private User user;
    private UserService userService;
    private String message;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String username =(String) parseObject.get("account");
        String password =(String) parseObject.get("password");
        String reader =(String) parseObject.get("name");
        String describe =(String) parseObject.get("describe");
        String cellphone =(String) parseObject.get("phone");
        String email =(String) parseObject.get("email");
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setReader(reader);
        user.setHeader("header");
        user.setDescribe(describe);
        user.setCellphone(cellphone);
        user.setEmail(email);
        user.setSex(true);
        userService=new UserService();
        final val b = userService.addUser(user);
        if (b) {
            message="添加成功";
        }else {
            message="添加失败";
        }
        req.getRequestDispatcher("/admin/UserList.jsp?message"+message).forward(req,resp);
    }
}
