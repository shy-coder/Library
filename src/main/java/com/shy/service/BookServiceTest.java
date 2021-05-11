package com.shy.service;

import com.shy.pojo.Book;
import org.junit.Test;

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

}