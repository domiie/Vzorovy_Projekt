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
    public Long createCustomer(@RequestBody CustomerDto customer){
        return customerService.createCustomer(customer);
    }

    //LIST CUSTOMERS
    @GetMapping("/api/customers")
    public List<CustomerDto> getCustomers(@RequestParam(required = false) String lastname){
        return customerService.getCustomers(lastname);
    }

    //GET CUSTOMER BY ID
    @GetMapping("/api/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId){
        return customerService.getCustomer(customerId);
    }

    //UPDATE CUSTOMER
    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerId, customerDto);
    }

    //DELETE CUSTOMER
    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
    }

}
