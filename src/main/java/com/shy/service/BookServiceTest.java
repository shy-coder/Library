package com.shy.service;

import com.shy.pojo.Book;
import com.shy.pojo.BorrowBook;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {

    BookService bookService = new BookService();

    @org.junit.Test
    public void addBook() {
        Book book = new Book("教父", "shy", "10", "看他个10遍，混的风生水起");
        boolean b = bookService.addBook(book);
        System.out.println(b);
    }

    @Test
    public void deleteBook() {
        boolean b = bookService.deleteBook(30 + "");
        System.out.println(b);
    }

    @Test
    public void updateBook() {
        Book book = new Book("29","教父", "哼哈二将", "10", "看他个10遍，混的风生水起");
        boolean b = bookService.updateBook(book);
        System.out.println(b);
    }

    @Test
    public void addBorrowBook() {
        boolean b = bookService.addBorrowBook("1805010222", "4");
        System.out.println(b);
    }

    @Test
    public void delBorrowBook() {
        boolean b = bookService.delBorrowBook("20");
        System.out.println(b);
    }

    @Test
    public void queryBorrowBooks() {
        List<BorrowBook> books = bookService.queryBorrowBooks("1805010222");
        System.out.println(books.toString());
    }


}