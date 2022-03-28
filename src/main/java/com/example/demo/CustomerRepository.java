package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    @Override
    List<CustomerEntity> findAll();
    Optional<CustomerEntity> findById(Long customerId);
}
