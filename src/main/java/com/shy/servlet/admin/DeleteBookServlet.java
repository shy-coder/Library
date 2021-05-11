package com.shy.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shy.service.BookService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "deleteBookServlet" ,urlPatterns = "/book/delete")
public class DeleteBookServlet extends HttpServlet {
    private BookService bookService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String id =(String) parseObject.get("id");
        bookService=new BookService();
        bookService.deleteBook(id);
        req.getRequestDispatcher("/admin/BookList.jsp").forward(req,resp);
    }
}
