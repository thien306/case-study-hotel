package com.codegym.controller;

import com.codegym.model.dto.CustomerDto;
import com.codegym.model.Customer;
import com.codegym.multipartFile.FileUploadUtil;
import com.codegym.security.JwtTokenProvider;
import com.codegym.service.Interface.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public ResponseEntity<?> updateCustomer(
            @RequestPart("customer") CustomerDto customerDto,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar,
            Authentication authentication) {

        String username = authentication.getName();
        Customer existingCustomer = customerService.getCustomerByUsername(username);

        if (existingCustomer == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        existingCustomer.setName(customerDto.getName());
        existingCustomer.setBirthday(customerDto.getBirthday());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());

        if (avatar != null && !avatar.isEmpty()) {
            try {
                String uploadDir = "C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\" + existingCustomer.getId();
                String avatarFileName = avatar.getOriginalFilename();
                FileUploadUtil.saveFile(uploadDir, avatarFileName, avatar);
                existingCustomer.setAvatar(uploadDir + avatarFileName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Error saving avatar file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        customerService.updateCustomer(existingCustomer);
        return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {

        String fileName = file.getOriginalFilename();
        try {
            file.transferTo( new File("C:\\upload\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

}
