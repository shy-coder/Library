package com.shy.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shy.service.UserService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "deleteUser",urlPatterns = "/user/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;
    private String message;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String userid =(String) parseObject.get("userid");
        System.out.println(userid);
        userService = new UserService();
        final boolean deleteUser = userService.deleteUser(userid);
        if (deleteUser) {
            message="删除成功";
        }else {
            message="删除失败";
        }
        req.getRequestDispatcher("/admin/UserList.jsp?message"+message).forward(req,resp);
    }
}
