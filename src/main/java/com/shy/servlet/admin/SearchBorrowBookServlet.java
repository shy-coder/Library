package com.shy.servlet.admin;

import com.shy.service.BookService;
import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/getBorrowBook")
public class SearchBorrowBookServlet extends HttpServlet {

    private BookService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service = new BookService();
        final val borrowBooks = service.queryAllBorrow();
        req.getSession().setAttribute("ADMIN_BORROW_BOOK",borrowBooks);
    }

}
