package com.shy.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shy.pojo.Book;
import com.shy.service.BookService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
@WebServlet(name = "updateBookServlet",urlPatterns = "/book/update")
public class UpdateBookServlet extends HttpServlet {
    private BookService bookService;
    private Book book;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String id =(String) parseObject.get("id");
        String name =(String) parseObject.get("name");
        String author =(String) parseObject.get("author");
        String sort =(String) parseObject.get("sort");
        String description =(String) parseObject.get("description");
        bookService=new BookService();
        book=new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setSort(sort);
        book.setDescription(description);
        final boolean b = bookService.updateBook(book);
        if (b){
            resp.sendRedirect("/admin/BookList.jsp");
        }else {
            req.getRequestDispatcher("/admin/BookList.jsp?message=" + "更新失败").forward(req, resp);
        }

    }
}
