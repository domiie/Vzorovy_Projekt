package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Hľadanie knihy podľa názvu
    @GetMapping("/api/books")
    public List<Book> getBooksByTitle(@RequestParam(required = false) String title){
        return bookService.getBooksByTitle(title);
    }

    //Hľadanie knihy podľa ID
    @GetMapping("/api/books/{bookId}")
    public Book getBookById(@PathVariable Integer bookId){
        return bookService.getBookById(bookId);
    }

    //Pridanie novej knihy
    @PostMapping("/api/books")
    public Integer createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    //Zmazanie knihy podľa ID
    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Integer bookId){
        bookService.deleteBook(bookId);
    }

    //Aktualizácia knihy podľa ID
    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Integer bookId, @RequestBody Book book){
        bookService.updateBook(bookId, book);
    }
}
