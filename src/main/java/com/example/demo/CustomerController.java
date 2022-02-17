package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customers;

    public CustomerController(){
        this.customers = init();
    }

    //INITIALIZE
    private List<Customer> init(){
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setFirstName("Alena");
        customer1.setLastName("Dobra");
        customer1.setContact("a.dobra@example.com");
        customers.add(customer1);
        customer1.setId((long) customers.indexOf(customer1));

        Customer customer2 = new Customer();
        customer2.setFirstName("Ivan");
        customer2.setLastName("Lexa");
        customer2.setContact("ivan.lexa@example.com");
        customers.add(customer2);
        customer2.setId((long) customers.indexOf(customer2));

        return customers;
    }

    //CREATE CUSTOMER
    @PostMapping("/api/customers")
    public String createCustomer(@RequestBody Customer customer){
        this.customers.add(customer);
        customer.setId((long) customers.size()-1);
        return "Customer with id "+(this.customers.size()-1)+" created.";
    }

    //LIST CUSTOMERS
    @GetMapping("/api/customers")
    public List<Customer> getCustomers(@RequestParam(required = false) String lastname){
        if(lastname == null){
            return this.customers;
        }

        List<Customer> filteredCustomers = new ArrayList<>();
        for(Customer customer : customers){
            if(customer.getLastName().equals(lastname)){
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }

    //GET CUSTOMER BY ID
    @GetMapping("/api/customers/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId){
        return this.customers.get(customerId);
    }

    //UPDATE CUSTOMER
    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer){
        this.customers.get(customerId).setFirstName(customer.getFirstName());
        this.customers.get(customerId).setLastName(customer.getLastName());
        this.customers.get(customerId).setContact(customer.getContact());
    }

    //DELETE CUSTOMER
    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId){
        this.customers.remove(this.customers.get(customerId));
    }

}
