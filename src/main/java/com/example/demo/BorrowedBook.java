package com.example.demo;

public class BorrowedBook {
    private Long id;
    private Customer customer;
    private Book book;

    public Long getId() {
        return id;
    }
    protected Customer getCustomer() {
        return customer;
    }
    protected Book getBook() {
        return book;
    }

    public Long getCustomerId(){
        return customer.getId();
    }
    public String getCustomerName(){
        return customer.getFirstName() + " " + customer.getLastName();
    }
    public Long getBookId(){
        return book.getId();
    }
    public String getAutorName(){
        return book.getAuthorFirstName() + " " + book.getAuthorLastName();
    }
    public String getTitle(){
        return book.getTitle();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
