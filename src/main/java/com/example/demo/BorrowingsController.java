package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BorrowingsController {
    private BorrowingsService borrowingsService;

    //GET ALL BORROWINGS
    @GetMapping("/api/borrowings")
    public List<BorrowedBook> getBorrowings(){
        return borrowingsService.getBorrowings();
    }
    //GET BORROWING BY ID
    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowedBook getBorrowingById(@PathVariable Integer borrowingId){
        return borrowingsService.getBorrowingById(borrowingId);
    }

    //CREATING NEW BORROWING
    @PostMapping("/api/borrowings/")
    public String createBorrowing(@RequestBody Long customerId, Long bookId){
        return borrowingsService.createBorrowing(customerId, bookId);
    }

    //DELETING BORROWING BY ID
    @DeleteMapping("/api/borrowings/{borrowingId}")
    public String deleteBorrowing(@PathVariable Integer borrowingId){
       return borrowingsService.deleteBorrowing(borrowingId);
    }

}
