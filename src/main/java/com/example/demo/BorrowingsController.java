package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class BorrowingsController {
    private List<BorrowedBook> borrowings;
    Customer customer;
    Book book;

    public BorrowingsController(){
        this.borrowings = init();
    }

    private List<BorrowedBook> init(){
        List<BorrowedBook> borrowings = new ArrayList<>();
        BorrowedBook borrowing1 = new BorrowedBook();
        borrowing1.setBorrower(customer);
        borrowing1.setBook(book);
        borrowings.add(borrowing1);
        borrowing1.setId((long) borrowings.indexOf(borrowing1));
        return borrowings;
    }

    @GetMapping("/api/borrowings")
    public List<BorrowedBook> getBorrowings(@RequestBody BorrowedBook borrowing){
        return this.borrowings;
    }

    //CREATE BORROWING
    @PostMapping("/api/borrowings")
    public String createBorrowings(@RequestBody BorrowedBook borrowing){
        this.borrowings.add(borrowing);
        borrowing.setId((long) borrowings.size()-1);
        return "Borrowing with id "+(this.borrowings.size()-1)+" was created.";
    }




}
