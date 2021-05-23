package com.shy.servlet;

import com.alibaba.fastjson.JSON;
import com.shy.service.BookService;
import lombok.val;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/delBorrowBook")
public class DelBorrowBookServlet extends HttpServlet {

    private BookService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service = new BookService();
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String bookId = (String) parseObject.get("bookId");
        final val b = service.delBorrowBook(bookId);
        if (b) {
            resp.sendRedirect("/borrowHistory.jsp");
        }
    }

}
