package org.example.models;

public class Book {
    private int id;

    private String title;

    private String author;

    private int year;

    public Book() {
    }

    public Book(int id, int year, String author, String title) {
        this.id = id;
        this.year = year;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
