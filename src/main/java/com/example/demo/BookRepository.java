package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//treba ukazat entitu a typ primarneho kluca, v nasom pripade Long
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>{
      @Override
      List<BookEntity> findAll();
}
