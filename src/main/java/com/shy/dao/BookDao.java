package com.shy.dao;

import com.shy.pojo.Book;
import com.shy.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public List<Book> selectAll(int pageNum, int pageSize) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select books.*, book_sort.name as sort " +
                "from books, book_sort where " +
                "books.sort_id=book_sort.id limit ?,?";
        Object[] params = {(pageNum - 1) * pageSize, pageSize};
        rs = JDBCUtil.execute(connection,pstm,rs,sql,params);
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book(rs.getString("name"),
                    rs.getString(   "author"),
                    rs.getString("sort"),
                    rs.getString("description"));
            books.add(book);
        }
        JDBCUtil.closeResource(connection, pstm, rs);
        return books;
    }

    public int count() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select count(*) as countNum from books";
        rs = JDBCUtil.execute(connection,pstm,rs,sql,new Object[]{});
        while (rs.next()) {
            int count = rs.getInt("countNum");
            return count;
        }
        JDBCUtil.closeResource(connection,pstm,rs);
        return 0;
    }
}
