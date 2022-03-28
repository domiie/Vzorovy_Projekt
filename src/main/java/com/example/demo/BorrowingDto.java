package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class BorrowingDto {
    private Long id;
    private Long bookId;
    private Long customerId;

    @JsonFormat(locale = "en", pattern = "dd MMMM yyyy")
    private LocalDate dateOfBorrowing;

    private long borrowingTerm;

    @JsonFormat(locale = "en", pattern = "dd MMMM yyyy")
    private LocalDate dateOfReturn;

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public long getBorrowingTerm() {
        return borrowingTerm;
    }

    public void setBorrowingTerm(long borrowingTerm) {
        this.borrowingTerm = borrowingTerm;
    }

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing(LocalDate dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
