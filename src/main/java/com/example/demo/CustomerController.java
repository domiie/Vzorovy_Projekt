package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    //CREATE CUSTOMER
    @PostMapping("/api/customers")
    public Long createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    //LIST CUSTOMERS
    @GetMapping("/api/customers")
    public List<Customer> getCustomers(@RequestParam(required = false) String lastname){
        return customerService.getCustomers(lastname);
    }

    //GET CUSTOMER BY ID
    @GetMapping("/api/customers/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId){
        return customerService.getCustomer(customerId);
    }

    //UPDATE CUSTOMER
    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer){
        customerService.updateCustomer(customerId, customer);
    }

    //DELETE CUSTOMER
    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
    }

}
