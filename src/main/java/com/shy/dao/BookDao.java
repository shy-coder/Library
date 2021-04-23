package com.shy.dao;

import com.shy.pojo.Book;
import com.shy.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            Book book = new Book(
                    rs.getInt("id") + "",
                    rs.getString("name"),
                    rs.getString("author"),
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

    public boolean selectStore(String username, String bookId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Object[] params = {bookId, username};
        String sql = "select EXISTS( SELECT 1 from borrow_books where book_id=? and card_id=?) as store";
        rs = JDBCUtil.execute(connection, pstm, rs, sql, params);
        while (rs.next()) {
            return rs.getBoolean("store");
        }
        return false;
    }

    public int insertStoreBook(String username, String bookId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        String sql = "insert into borrow_books(book_id, card_id, borrow_date) values(?,?,?)";
        Object[] params = {bookId, username, new Date(System.currentTimeMillis())};
        int rs = JDBCUtil.execute(connection, pstm, sql, params);
        return rs;
    }
}
