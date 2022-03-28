package com.example.demo;

import javax.transaction.Transactional;

import com.example.demo.BorrowingEntity;
import com.example.demo.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    private BookRepository bookRepository;
    private CustomerRepository customerRepository;
    private BorrowingRepository borrowingRepository;

    private static BorrowingListDto mapToBorrowingsDto(BorrowingEntity borrowingEntity){
        BorrowingListDto borrowingListDto = new BorrowingListDto();

        borrowingListDto.setId(borrowingEntity.getId());
        borrowingListDto.setCustomerId(borrowingEntity.getCustomer().getId());
        borrowingListDto.setCustomerName(borrowingEntity.getCustomer().getFirstname()+ " " +borrowingEntity.getCustomer().getLastname());
        borrowingListDto.setBookId(borrowingEntity.getBook().getId());
        borrowingListDto.setBookTitle(borrowingEntity.getBook().getTitle());
        borrowingListDto.setDateOfBorrowing(borrowingEntity.getDateOfBorrowing());
        borrowingListDto.setBorrowingTerm(borrowingEntity.getBorrowingTerm());
        borrowingListDto.setDateOfReturn(borrowingEntity.getDateOfReturn());

        return borrowingListDto;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate dateReturn =  LocalDate.now().plusDays(borrowingDto.getBorrowingTerm());
//        String returnDate = dateReturn.format(formatter);
//        String currentDate = LocalDate.now().format(formatter);

        if(b1.isPresent() && c1.isPresent() && bookEntity.getBookCount()>0) {
            borrowing.setBorrowingTerm(borrowingDto.getBorrowingTerm());
            bookEntity.setBookCount(bookEntity.getBookCount()-1);
            borrowing.setBook(bookEntity);
            borrowing.setCustomer(c1.get());
            borrowing.setDateOfBorrowing(currentDate);
            borrowing.setDateOfReturn(dateReturn);
            this.borrowingRepository.save(borrowing);
        }

        return borrowing.getId();
    }

    @Transactional
    public BorrowingListDto getBorrowing(Long borrowingId){
        Optional<BorrowingEntity> byId = borrowingRepository.findById(borrowingId);
        if(byId.isPresent()){
            return  mapToBorrowingsDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<BorrowingListDto> getBorrowings(Long borrowingId) {
        List<BorrowingListDto> borrowings = new LinkedList<>();
        for (BorrowingEntity b1 : borrowingRepository.findAll()) {
            BorrowingListDto b2 = mapToBorrowingsDto(b1);
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