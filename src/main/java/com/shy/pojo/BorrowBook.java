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
}

