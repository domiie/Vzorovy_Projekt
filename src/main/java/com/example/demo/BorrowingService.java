package com.example.demo;

import javax.transaction.Transactional;

import com.example.demo.BorrowingEntity;
import com.example.demo.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    private BookRepository bookRepository;
    private CustomerRepository customerRepository;
    private BorrowingRepository borrowingRepository;

    private static BorrowingDto mapToBorrowingsDto(BorrowingEntity borrowingEntity){
        BorrowingDto borrowingDto = new BorrowingDto();

        borrowingDto.setId(borrowingEntity.getId());
        borrowingDto.setCustomerId(borrowingEntity.getCustomer().getId());
        borrowingDto.setBookId(borrowingEntity.getBook().getId());

        return borrowingDto;
    }

    public BorrowingService(BorrowingRepository borrowingRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long createBorrowing(BorrowingDto borrowingDto){
        BorrowingEntity borrowing = new BorrowingEntity();

        Optional<BookEntity> b1 = bookRepository.findById(borrowingDto.getBookId());
        Optional<CustomerEntity> c1 = customerRepository.findById(borrowingDto.getCustomerId());
        BookEntity bookEntity = b1.get();

        if(b1.isPresent() && c1.isPresent() && bookEntity.getBookCount()>0) {
            bookEntity.setBookCount(bookEntity.getBookCount()-1);
            borrowing.setBook(bookEntity);
            borrowing.setCustomer(c1.get());
            this.borrowingRepository.save(borrowing);
        }

        return borrowing.getId();
    }

    @Transactional
    public BorrowingDto getBorrowing(Long borrowingId){
        Optional<BorrowingEntity> byId = borrowingRepository.findById(borrowingId);
        if(byId.isPresent()){
            return  mapToBorrowingsDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<BorrowingDto> getBorrowings(Long borrowingId) {
        List<BorrowingDto> borrowings = new LinkedList<>();
        for (BorrowingEntity b1 : borrowingRepository.findAll()) {
            BorrowingDto b2 = mapToBorrowingsDto(b1);
            borrowings.add(b2);
        }
        return borrowings;
    }

    @Transactional
    public void deleteBorrowing(Long borrowingId){
        Optional<BorrowingEntity> byId = borrowingRepository.findById(borrowingId);
        if (byId.isPresent()) {
            BorrowingEntity b1 = byId.get();
            b1.getBook().setBookCount(b1.getBook().getBookCount()+1);
            borrowingRepository.delete(byId.get());
        }
    }

}