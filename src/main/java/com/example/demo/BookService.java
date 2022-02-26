package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books;

    public BookService(){
        this.books = init();
    }

    private List<Book> init(){
        List<Book> books = new ArrayList<>();

        Book book1 = new Book();
        book1.setAuthorFirstName("Arthur");
        book1.setAuthorLastName("Doyle");
        book1.setTitle("Study in Red");
        book1.setIsbn("974-AD-41-C-F");
        book1.setBookCount(17);
        books.add(book1);
        book1.setId((long) books.indexOf(book1));

        Book book2 = new Book();
        book2.setAuthorFirstName("J.R.R.");
        book2.setAuthorLastName("Tolkien");
        book2.setTitle("The Hobbit");
        book2.setIsbn("128-XD-77-Q-F");
        book2.setBookCount(12);
        books.add(book2);
        book2.setId((long) books.indexOf(book2));

        return books;
    }


    public List<Book> getBooksByTitle(String title){

        if(title == null){
            for (Book book : books){
                return this.books;
            }
        }

        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books){
            if(book.getTitle().equals(title)){
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public Book getBookById(Integer bookId){
        return this.books.get(bookId);
    }

    public Integer createBook(Book book){
        this.books.add(book);
        return this.books.size() - 1;
    }

    public void deleteBook(Integer bookId){
        this.books.remove(this.books.get(bookId));
    }

    public void updateBook(Integer bookId, Book book){
        this.books.get(bookId).setTitle(book.getTitle());
        this.books.get(bookId).setAuthorFirstName(book.getAuthorFirstName());
        this.books.get(bookId).setAuthorLastName(book.getAuthorLastName());
        this.books.get(bookId).setBookCount(book.getBookCount());
        this.books.get(bookId).setIsbn(book.getIsbn());
        this.books.get(bookId).setId(book.getId());
    }

}