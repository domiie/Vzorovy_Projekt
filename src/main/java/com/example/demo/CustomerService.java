package com.example.demo;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    private static Customer mapToCustomer(CustomerEntity customerEntity){
        Customer customer = new Customer();

        customer.setFirstName(customerEntity.getFirstname());
        customer.setLastName(customerEntity.getLastname());
        customer.setContact(customerEntity.getContact());

        return customer;
    }

    //CREATE
    @Transactional
    public Long createCustomer(Customer customer){
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstname(customer.getFirstName());
        customerEntity.setLastname(customer.getLastName());
        customerEntity.setContact(customer.getContact());

        this.customerRepository.save(customerEntity);

        return customerEntity.getId();
    }

    //LIST CUSTOMERS
    @Transactional
    public List<Customer> getCustomers(String lastname){
        List<Customer> cust = new LinkedList<>();
        for(CustomerEntity c1 : customerRepository.findAll()){
            Customer c2 = mapToCustomer(c1);
            cust.add(c2);
        }
        return cust;
    }

    //GET CUSTOMER BY ID
    @Transactional
    public Customer getCustomer(Integer customerId){
        //TODO DOROBIT
        return  null;
    }

    //UPDATE CUSTOMER
    @Transactional
    public void updateCustomer(Integer customerId, Customer customer){
        //TODO DOROBIT
    }

    //DELETE CUSTOMER
    @Transactional
    public void deleteCustomer(Integer customerId){
        //TODO DOROBIT
    }

}
