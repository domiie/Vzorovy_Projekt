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
    private final FolderRepository folderRepository;

    public BookService(BookRepository bookRepository, FolderRepository folderRepository){
        this.bookRepository = bookRepository;
        this.folderRepository = folderRepository;
    }

    private List<FolderEntity> mapFolderToFolderEntity(List<Long> folderIds){
        List<FolderEntity> folderEntities = new ArrayList<>();
        for(Long folderId: folderIds){
            folderEntities.add(folderRepository.findById(folderId).get()) ;
        }
      return folderEntities;
    }

    private static BookDto mapToBookDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthorName(bookEntity.getAuthorName());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setIsbn(bookEntity.getIsbn());
        bookDto.setId(bookEntity.getId());
        bookDto.setBookCount(bookEntity.getBookCount());
        bookDto.setNumberOfPages(bookEntity.getNumberOfPages());
        bookDto.setGenres(bookEntity.getGenres());
        List<Long> folderIds = new ArrayList<>();
        for(FolderEntity folder: bookEntity.getFolderEntity()){
             folderIds.add(folder.getFolderId());
        }
        bookDto.setFolderIds(folderIds);

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
        bookEntity.setNumberOfPages(book.getNumberOfPages());
        bookEntity.setGenres(book.getGenres());
        List<FolderEntity> folderEntities = mapFolderToFolderEntity(book.getFolderIds());
        bookEntity.setFolderEntity(folderEntities);
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
            byId.get().setNumberOfPages(bookDto.getNumberOfPages());
            byId.get().setGenres(bookDto.getGenres());
        }

    }
}

