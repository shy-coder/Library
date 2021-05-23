package com.shy.servlet;

import com.shy.service.BookService;
import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getBorrowBooks")
public class SearchBorrowBookServlet extends HttpServlet {

    private BookService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service = new BookService();
        final val session = req.getSession();
        final val userId = session.getAttribute("userId");
        System.out.println(userId);
        final val bookList = service.queryBorrowBooks((String) userId);
        System.out.println(bookList.toString());
        session.setAttribute("bookList",bookList);
    }

}
