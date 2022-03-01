package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    public List<BookDto> getListOfBooks(){
        return null;
    }

    public List<BookDto> getBooksByTitle(String title){
        return null;
    }

    public BookDto getBookById(Integer bookId){
        return null;
    }

    @Transactional
    public Long createBook(BookDto book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthorFirstName());
        bookEntity.setTitle(book.getTitle());
        this.bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    public void deleteBook(Integer bookId){

    }

    public void updateBook(Integer bookId, BookDto book){

    }
}
