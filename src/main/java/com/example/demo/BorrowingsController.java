package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BorrowingsController {
    //LIST OF ALL BORROWINGS
    private List<BorrowedBook> borrowings;
    //LISTS OF CUSTOMERS AND BOOKS
    CustomerController customerController = new CustomerController();
    BookController bookController = new BookController();
    List<Customer> customers = customerController.getListOfCustomers();
    List<Book> books = bookController.getListOfBooks();
    //DEFINITIONS OF CUSTOMER AND BOOK
    Customer customer;
    Book book;
    public BorrowingsController(){
        this.borrowings = init();
    }

    //INIT
    private List<BorrowedBook> init(){
        List<BorrowedBook> borrowings = new ArrayList<>();
        BorrowedBook borrowing1 = new BorrowedBook();
        customer = customers.get(0);
        book = books.get(0);
        borrowing1.setCustomer(customer);
        borrowing1.setBook(book);
        borrowings.add(borrowing1);
        borrowing1.setId((long) borrowings.indexOf(borrowing1));

        return borrowings;
    }

    //GET ALL BORROWINGS
    @GetMapping("/api/borrowings")
    public List<BorrowedBook> getBorrowings(){
        return this.borrowings;
    }
    //GET BORROWING BY ID
    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowedBook getBorrowingById(@PathVariable Integer borrowingId){
        return this.borrowings.get(borrowingId);
    }

    //CREATING NEW BORROWING
    @PostMapping("/api/borrowings/")
    public String createBorrowing(@RequestBody BorrowedBook borrowedBook){
        Long bookId = borrowedBook.bookId;
        Long customerId = borrowedBook.customerId;
        customer = customers.get(Math.toIntExact(customerId));
        book = books.get(Math.toIntExact(bookId));
        BorrowedBook newBorrowing = new BorrowedBook();
        newBorrowing.setCustomer(customer);
        newBorrowing.setBook(book);
        borrowings.add(newBorrowing);
        newBorrowing.setId((long) borrowings.indexOf(newBorrowing));
        return "Borrowing with id "+(this.borrowings.size()-1)+" was created.";
    }

    //DELETING BORROWING BY ID
    @DeleteMapping("/api/borrowings/{borrowingId}")
    public String deleteBorrowing(@PathVariable Integer borrowingId){
        this.borrowings.remove(this.borrowings.get(borrowingId));
        return "Borrowing with id "+(this.borrowings.size()-1)+" was deleted.";
    }

}
