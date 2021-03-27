package com.shy.pojo;

public class User {
    private String username;
    private String password;
    private String reader;

    public User(String username, String password, String reader) {
        this.username = username;
        this.password = password;
        this.reader = reader;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }
}
