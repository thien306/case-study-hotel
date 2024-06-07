package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.response.ForbiddenResponse;
import com.codegym.payload.response.LoginResponse;
import com.codegym.security.JwtTokenProvider;
import com.codegym.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest ) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gọi hàm tạo Token
            String token = tokenProvider.generateToken(authentication);
            return new ResponseEntity<>(new LoginResponse("Login success!", token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new LoginResponse("Login failed!", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/access-denied")
    public ResponseEntity<?> getAccessDenied() {
        return new ResponseEntity<>(new ForbiddenResponse("Unauthorized access!"), HttpStatus.FORBIDDEN);
    }

    @PostMapping("/customers/login")
    public ResponseEntity<?> login(@RequestBody Customer loginCustomer) {
        Customer customer = customerService.getCustomerByEmail(loginCustomer.getEmail());
        if (customer != null && customer.getPassword().equals(loginCustomer.getPassword())) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
