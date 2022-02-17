package com.example.demo;

public class BorrowedBook {
    private Long id;
    private Customer borrower;
    private Book book;

    public Long getId() {
        return id;
    }

    public Customer getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBorrower(Customer borrower) {
        this.borrower = borrower;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
