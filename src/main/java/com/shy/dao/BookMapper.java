package com.shy.dao;

import com.shy.pojo.Book;
import com.shy.pojo.BorrowBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    int addBook(Book book);

    int deleteBook(String id);

    int updateBook(Book book);
    
    int addBorrowBook(@Param("userId") String userId, @Param("bookId") String bookId);

    int deleteBorrowBook(String id);

    List<BorrowBook> queryBorrowBooks(String userId);

    List<BorrowBook> queryAllBorrow();
}
