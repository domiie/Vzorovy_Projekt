package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowingsService {
    private List<BorrowedBook> borrowings;
    Customer customer;
    BookDto book;
    private BookService bookService;
    private CustomerService customerService;
    //private List<Customer> customers = customerService.getListOfCustomers()
    private List<BookDto> books = bookService.getListOfBooks();
    public BorrowingsService(){
        this.borrowings = init();
    }

    //INIT
    private List<BorrowedBook> init(){
        List<BorrowedBook> borrowings = new ArrayList<>();
        BorrowedBook borrowing1 = new BorrowedBook();
       // customer = customers.get(0);
        book = books.get(0);
        borrowing1.setCustomer(customer);
        borrowing1.setBook(book);
        borrowings.add(borrowing1);
        borrowing1.setId((long) borrowings.indexOf(borrowing1));
        return borrowings;
    }
    //GET ALL BORROWINGS
    public List<BorrowedBook> getBorrowings(){
        return this.borrowings;
    }
    //GET BORROWINGS BY ID
    public BorrowedBook getBorrowingById(Integer borrowingId){
        return this.borrowings.get(borrowingId);
    }
    //CREATING NEW BORROWING
    public String createBorrowing(Long customerId, Long bookId){
     //   customer = customers.get(Math.toIntExact(customerId));
        book = books.get(Math.toIntExact(bookId));
        BorrowedBook newBorrowing = new BorrowedBook();
        newBorrowing.setCustomer(customer);
        newBorrowing.setBook(book);
        borrowings.add(newBorrowing);
        newBorrowing.setId((long) borrowings.indexOf(newBorrowing));
        return "Borrowing with id "+(this.borrowings.size()-1)+" was created.";
    }
    //DELETING BORROWING BY ID
    public String deleteBorrowing(Integer borrowingId){
        this.borrowings.remove(this.borrowings.get(borrowingId));
        return "Borrowing with id "+(this.borrowings.size()-1)+" was deleted.";
    }
}