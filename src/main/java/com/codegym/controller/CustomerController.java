package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer == null) {
            return new ResponseEntity<>("Customer Not Found" ,HttpStatus.NOT_FOUND);
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setBirthday(customer.getBirthday());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAvatar(customer.getAvatar());

        if (!customer.getPassword().isEmpty()) {
            existingCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }

        customerService.updateCustomer(existingCustomer);
        return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
    }
}
