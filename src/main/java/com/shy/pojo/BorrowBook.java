package com.shy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBook {
    private String id;
    private String bookID;
    private String name;
    private String author;
    private String sort;
    private String description;
    private boolean store;

    private String username;
    private String reader;

    public BorrowBook(String id, String bookID, String name, String author, String sort, String description, boolean store) {
        this.id = id;
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
        this.store = store;
    }

    public BorrowBook(String id, String name, String author, String username, String reader) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.username = username;
        this.reader = reader;
    }
}

