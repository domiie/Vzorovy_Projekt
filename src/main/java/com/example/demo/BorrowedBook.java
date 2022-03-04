package com.example.demo;

public class BorrowedBook {
    private Long id;
    private Customer customer;
    private BookDto bookDto;
    protected Long customerId;
    protected Long bookId;

    public Long getId() {
        return id;
    }
    protected Customer getCustomer() {
        return customer;
    }
    protected BookDto getBook() {
        return bookDto;
    }

    public Long getCustomerId(){
        return customer.getId();
    }
    public String getCustomerName(){
        return customer.getFirstName() + " " + customer.getLastName();
    }
    public Long getBookId(){
        return bookDto.getId();
    }
    public String getAutorName(){
        return bookDto.getAuthorFirstName() + " " + bookDto.getAuthorLastName();
    }
    public String getTitle(){
        return bookDto.getTitle();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setBook(BookDto bookDto) {
        this.bookDto = bookDto;
    }
}
