package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.security.JwtTokenProvider;
import com.codegym.service.Interface.ICustomerService;
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

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
//    @PreAuthorize("permitAll()")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update-by-username")
//    @PreAuthorize("permitAll()")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        Customer existingCustomer = customerService.getCustomerByUsername(username);
        if (existingCustomer == null) {
            return new ResponseEntity<>("Customer not found" ,HttpStatus.NOT_FOUND);
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setBirthday(customer.getBirthday());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAvatar(customer.getAvatar());

        customerService.updateCustomer(existingCustomer);
        return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
    }
}
