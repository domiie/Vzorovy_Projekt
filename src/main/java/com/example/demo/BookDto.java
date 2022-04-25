package com.example.demo;

public class BookDto {
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private Long id;
    private int bookCount;
    private int numberOfPages;
    private String genres;

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getId() {
        return id;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
