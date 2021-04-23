package com.shy.service;

import com.shy.dao.BookDao;
import com.shy.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> searchAllBooks(String username, int pageNum, int pageSize) {
        List<Book> books = null;
        try {
            books = bookDao.selectAll(pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
        }
        return books;
    }

    public int countNum() {
        int rs = -1;
        try {
            rs =  bookDao.count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean isStore(String username, String bookId) {
        boolean rs = false;
        try {
            rs = bookDao.selectStore(username, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String storeBook(String username, String bookId) {
        int result = 0;
        try {
            result = bookDao.insertStoreBook(username, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return "借阅成功";
        } else {
            return "借阅失败";
        }
    }
}
