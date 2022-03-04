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

    @Transactional
    public BookDto getListOfBooks(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.getAuthorFirstName();
        bookEntity.getAuthorLastName();
        bookEntity.getTitle();
        bookEntity.getIsbn();
        bookEntity.getId();
        bookEntity.getBookCount();
        this.bookRepository.save(bookEntity);
        return bookEntity;
    }

//    @Transactional
//    public String getBooksByTitle(String title){
//        if(title == null){
//            for (BookDto bookDto : bookDtos){
//                return this.bookDtos;
//            }
//        }
//
//        List<BookDto> filteredBookDtos = new ArrayList<>();
//
//        for (BookDto bookDto : bookDtos){
//            if(bookDto.getTitle().equals(title)){
//                filteredBookDtos.add(bookDto);
//            }
//        }
//        return filteredBookDtos;
//    }

    @Transactional
    public String getBooksByTitle(String title){
        BookEntity bookEntity = new BookEntity();
        bookEntity.getAuthorFirstName();
        bookEntity.getAuthorLastName();
        bookEntity.getTitle();
        bookEntity.getIsbn();
        bookEntity.getId();
        bookEntity.getBookCount();
        this.bookRepository.save(bookEntity);
        if(title == null){
            return bookEntity.getTitle();
        }

        List<BookDto> filteredBookDtos = new ArrayList<>();

        if(bookEntity.getTitle().equals(title)){
            return bookEntity.getTitle();
        }
        return bookEntity.getTitle();
    }

    @Transactional
    public Long getBookById(Integer bookId){
        BookEntity bookEntity = new BookEntity();
        bookEntity.getAuthorFirstName();
        bookEntity.getAuthorLastName();
        bookEntity.getTitle();
        bookEntity.getIsbn();
        bookEntity.getId();
        bookEntity.getBookCount();
        this.bookRepository.save(bookEntity);
        return bookEntity.getId();
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
    public void deleteBook(Integer bookId){
        BookEntity bookEntity = new BookEntity();
        bookEntity.getAuthorFirstName();
        bookEntity.getAuthorLastName();
        bookEntity.getTitle();
        bookEntity.getIsbn();
        bookEntity.getId();
        bookEntity.getBookCount();
        this.bookRepository.save(bookEntity);
        this.bookRepository.delete(bookEntity);
    }

    @Transactional
    public void updateBook(Integer bookId, BookDto bookDto){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorFirstName(bookDto.getAuthorFirstName());
        bookEntity.setAuthorLastName(bookDto.getAuthorLastName());
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setIsbn(bookDto.getIsbn());
        bookEntity.setId(bookDto.getId());
        bookEntity.setBookCount(bookDto.getBookCount());
        this.bookRepository.save(bookEntity);
//        this.bookEntity.get(bookId).setTitle(bookDto.getTitle());
//        this.bookDtos.get(bookId).setAuthorFirstName(bookDto.getAuthorFirstName());
//        this.bookDtos.get(bookId).setAuthorLastName(bookDto.getAuthorLastName());
//        this.bookDtos.get(bookId).setBookCount(bookDto.getBookCount());
//        this.bookDtos.get(bookId).setIsbn(bookDto.getIsbn());
//        this.bookDtos.get(bookId).setId(bookDto.getId());
    }
}
