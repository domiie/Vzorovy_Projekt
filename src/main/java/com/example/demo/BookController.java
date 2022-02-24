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

    //    @GetMapping("/api/books")
//    public List<String> getBooksByTitle(@RequestParam(required = false) String title){
//
//        List<String> nonFilteredBooks = new ArrayList<>();
//
//        if(title == null){
//            for (Book book : books){
//                nonFilteredBooks.add(book.getTitle());
//            }
//            return nonFilteredBooks;
//        }
//
//        List<String> filteredBooks = new ArrayList<>();
//
//        for (Book book : books){
//            if(book.getTitle().equals(title)){
//                filteredBooks.add(book.getTitle());
//            }
//        }
//        return filteredBooks;
//    }

    //Hľadanie knihy podľa názvu
    @GetMapping("/api/books")
    public List<Book> getBooksByTitle(@RequestParam(required = false) String title){
        return bookService.getBooksByTitle(title);
    }


    //Hľadanie knihy podľa ID
    @GetMapping("/api/books/{bookId}")
    public List<Book> getBookById(@PathVariable Integer bookId){
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
