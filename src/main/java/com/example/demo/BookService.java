package com.example.demo;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    private static BookDto mapToBookDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();

        bookDto.setAuthorFirstName(bookEntity.getAuthorFirstName());
        bookDto.setAuthorLastName(bookEntity.getAuthorLastName());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setIsbn(bookEntity.getIsbn());
        bookDto.setId(bookEntity.getId());
        bookDto.setBookCount(bookEntity.getBookCount());

        return bookDto;
    }

    @Transactional
    public List<BookDto> getListOfBooks(){
        List<BookDto> books = new LinkedList<>();

        for (BookEntity b1 : bookRepository.findAll()){
            BookDto b2 = mapToBookDto(b1);
            books.add(b2);
        }
        return books;
    }

    @Transactional
    public BookDto getBooksByTitle(String title){
        Optional<BookEntity> byTitle = bookRepository.findByTitle(title);
        if(byTitle.isPresent()){
            return mapToBookDto(byTitle.get());
        }
        return null;
    }

    @Transactional
    public BookDto getBookById(Long bookId){
        Optional<BookEntity> byId = bookRepository.findById(bookId);
        if(byId.isPresent()){
            return mapToBookDto(byId.get());
        }
        return null;
    }

    @Transactional
    public Long createBook(BookDto book){
        //vytvarame novu entitu
        BookEntity bookEntity = new BookEntity();
        //nastavie name and title
        bookEntity.setAuthorFirstName(book.getAuthorFirstName());
        bookEntity.setAuthorLastName(book.getAuthorLastName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setId(book.getId());
        bookEntity.setBookCount(book.getBookCount());
        //ulozime
        this.bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    @Transactional
    public void deleteBook(Long bookId){
        Optional<BookEntity> byId = bookRepository.findById(bookId);
        if (byId.isPresent()) {
            bookRepository.delete(byId.get());
        }
    }

    @Transactional
    public void updateBook(Long bookId, BookDto bookDto){
        Optional<BookEntity> byId = bookRepository.findById(bookId);
        if (byId.isPresent()) {
            byId.get().setAuthorFirstName(bookDto.getAuthorFirstName());
            byId.get().setAuthorLastName(bookDto.getAuthorLastName());
            byId.get().setTitle(bookDto.getTitle());
            byId.get().setIsbn(bookDto.getIsbn());
            byId.get().setId(bookDto.getId());
            byId.get().setBookCount(bookDto.getBookCount());
        }
    }
}

