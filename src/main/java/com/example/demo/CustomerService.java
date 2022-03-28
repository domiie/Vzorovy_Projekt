package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    private static CustomerDto mapToCustomerDto(CustomerEntity customerEntity){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customerEntity.getId());
        customerDto.setFirstName(customerEntity.getFirstname());
        customerDto.setLastName(customerEntity.getLastname());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setPhoneNumber(customerEntity.getPhoneNumber());

        return customerDto;
    }

    //CREATE
    @Transactional
    public Long createCustomer(CustomerDto customer){
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstname(customer.getFirstName());
        customerEntity.setLastname(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());

        this.customerRepository.save(customerEntity);

        return customerEntity.getId();
    }

    //LIST CUSTOMERS
    @Transactional
    public List<CustomerDto> getCustomers(String lastname){
        List<CustomerDto> customers = new LinkedList<>();
        for(CustomerEntity c1 : customerRepository.findAll()){
            CustomerDto c2 = mapToCustomerDto(c1);
            customers.add(c2);
        }
        return customers;
    }

    //GET CUSTOMER BY ID
    @Transactional
    public CustomerDto getCustomer(Long customerId){
        Optional<CustomerEntity> byId = customerRepository.findById(customerId);
        if(byId.isPresent()){
            return  mapToCustomerDto(byId.get());
        }
        return null;
    }

    //UPDATE CUSTOMER
    @Transactional
    public void updateCustomer(Long customerId, CustomerDto customerDto){
        Optional<CustomerEntity> byId = customerRepository.findById(customerId);

        if (byId.isPresent()) {
            byId.get().setFirstname(customerDto.getFirstName());
            byId.get().setLastname(customerDto.getLastName());
            byId.get().setEmail(customerDto.getEmail());
            byId.get().setPhoneNumber(customerDto.getPhoneNumber());
        }

    }
    //DELETE CUSTOMER
    @Transactional
    public void deleteCustomer(Long customerId){
        Optional<CustomerEntity> byId = customerRepository.findById(customerId);
        if (byId.isPresent()) {
            customerRepository.delete(byId.get());
        } //TODO SPRAVA CHYBY KED SA NEPODARILO VYMAZAT (throw)
    }

}
