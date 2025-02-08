package com.form.securelogin.model;

public class Book {
    private String title;
    private String bookLink;
    private String author;
    private String authorLink;
    private String imageUrl;

    public Book(String title, String bookLink, String author, String authorLink, String imageUrl) {
        this.title = title;
        this.bookLink = bookLink;
        this.author = author;
        this.authorLink = authorLink;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

