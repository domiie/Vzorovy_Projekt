package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class BorrowingEntity{

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dateOfBorrowing;
    private long borrowingTerm;
    private LocalDate dateOfReturn;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing(LocalDate dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public long getBorrowingTerm() {
        return borrowingTerm;
    }

    public void setBorrowingTerm(long borrowingTerm) {
        this.borrowingTerm = borrowingTerm;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

}

