package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
