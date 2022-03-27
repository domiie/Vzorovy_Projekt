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

        bookDto.setId(bookEntity.getId());
        bookDto.setAuthorName(bookEntity.getAuthorName());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setIsbn(bookEntity.getIsbn());
        bookDto.setBookCount(bookEntity.getBookCount());
        bookDto.setGenres(bookEntity.getGenres());
        bookDto.setNum_pages(bookEntity.getNum_pages());

        return bookDto;
    }

    @Transactional
    public List<BookDto> getBooks(String bookAuthor) {
        List<BookDto> books = new LinkedList<>();
        for (BookEntity b1 : bookRepository.findAll()) {
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
        bookEntity.setAuthorName(book.getAuthorName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setBookCount(book.getBookCount());
        bookEntity.setGenres(book.getGenres());
        bookEntity.setNum_pages(book.getNum_pages());
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
            byId.get().setAuthorName(bookDto.getAuthorName());
            byId.get().setTitle(bookDto.getTitle());
            byId.get().setIsbn(bookDto.getIsbn());
            byId.get().setBookCount(bookDto.getBookCount());
            byId.get().setGenres(bookDto.getGenres());
            byId.get().setNum_pages(bookDto.getNum_pages());
        }
    }
}