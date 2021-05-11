package com.shy.dao;

import com.shy.pojo.Book;

public interface UserMapper {

    int addBook(Book book);

    int deleteBook(String id);

    int updateBook(Book book);

}
