package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>  {
    @Override
    List<BookEntity> findAll();
    Optional<BookEntity> findById(Long bookId);
}

