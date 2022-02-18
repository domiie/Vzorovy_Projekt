package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BorrowingsController {
    private List<BorrowedBook> borrowings;
    Customer customer;
    Book book;
    public BorrowingsController(){
        this.borrowings = init();
    }
    //INIT
    private List<BorrowedBook> init(){
        List<BorrowedBook> borrowings = new ArrayList<>();
        BorrowedBook borrowing1 = new BorrowedBook();
        //manualne objekty
        customer = new Customer();
        customer.setFirstName("Alena");
        customer.setLastName("Dobra");
        customer.setContact("a.dobra@example.com");
        book = new Book();
        book.setAuthorFirstName("Arthur");
        book.setAuthorLastName("Doyle");
        book.setTitle("Study in Red");
        book.setIsbn("974-AD-41-C-F");
        book.setBookCount(17);
        //pridavame instancie BOOKa a CUSTOMERa do pozicky
        borrowing1.setCustomer(customer);
        borrowing1.setBook(book);
        borrowings.add(borrowing1);
        borrowing1.setId((long) borrowings.indexOf(borrowing1));
        return borrowings;
    }
    //GET BORROWING BY BOOK TITLE
    @GetMapping("/api/borrowings")
    public List<BorrowedBook> getBorrowingsByBookTitle(@RequestParam(required = false) String title){

        if(title == null){
            for (BorrowedBook borrow : borrowings){
                return this.borrowings;
            }
        }

        List<BorrowedBook> filteredBorrowings = new ArrayList<>();

        for (BorrowedBook borrow : borrowings){
            if(borrow.getBook().getTitle().equals(title)){
                filteredBorrowings.add(borrow);
            }
        }
        return filteredBorrowings;
    }
    //CREATE NEW BORROWING
    //iba testova metoda
    @PostMapping("/api/borrowings")
    public String createBorrowing(@RequestBody BorrowedBook borrowedBook){
        this.borrowings.add(borrowedBook);
        borrowedBook.setId((long) borrowings.size()-1);
        return "Borrowing with id "+(this.borrowings.size()-1)+" was created.";
    }


}
