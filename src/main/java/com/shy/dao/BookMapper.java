package com.shy.dao;

import com.shy.pojo.Book;

public interface BookMapper {

    int addBook(Book book);

    int deleteBook(String id);

    int updateBook(Book book);

}
