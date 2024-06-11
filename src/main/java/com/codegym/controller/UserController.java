package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(){
        System.out.println("hello");
        return null;
    }
}
