package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BorrowingController {
    private BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/api/borrowings")
    public List<BorrowingListDto> getBooks(@RequestParam(required = false) Long borrowingId) {
        return borrowingService.getBorrowings(borrowingId);
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowingListDto getAllBorrowings(@PathVariable Long borrowingId) {
       return borrowingService.getBorrowing(borrowingId);
    }

    @PostMapping("/api/borrowings")
    public Long createBorrowing(@RequestBody BorrowingDto borrowingDto){
        return borrowingService.createBorrowing(borrowingDto);
    }

    //DELETE CUSTOMER
    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId){
        borrowingService.deleteBorrowing(borrowingId);
    }

}
