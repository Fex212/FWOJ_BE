package com.teleport.pojo;



public class ann {

    private String date;
    private String title;
    private String content;
    private int author_id;
    private String author_username;

    public ann(String date, String title, String content, int author_id, String author_username) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.author_username = author_username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_user() {
        return author_username;
    }

    public void setAuthor_user(String author_user) {
        this.author_username = author_user;
    }



}
