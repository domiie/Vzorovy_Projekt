package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //Hľadanie knihy podľa názvu
    @GetMapping("/api/books")
    public BookDto getBooksByTitle(@RequestParam(required = false) String title){
        return bookService.getBooksByTitle(title);
    }

    //Hľadanie knihy podľa ID
    @GetMapping("/api/books/{bookId}")
    public BookDto getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }

    //Pridanie novej knihy
    @PostMapping("/api/books")
    public Long createBook(@RequestBody BookDto bookDto){
       return bookService.createBook(bookDto);
    }

    //Zmazanie knihy podľa ID
    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }

    //Aktualizácia knihy podľa ID
    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Long bookId, @RequestBody BookDto bookDto){
        bookService.updateBook(bookId, bookDto);
    }
}

