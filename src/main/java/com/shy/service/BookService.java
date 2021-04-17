package com.shy.service;

import com.shy.dao.BookDao;
import com.shy.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> searchAllBooks(int pageNum, int pageSize) {

        List<Book> books = null;
        try {
            books = bookDao.selectAll(pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public int countNum() {
        int count = -1;
        try {
            count = bookDao.count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
