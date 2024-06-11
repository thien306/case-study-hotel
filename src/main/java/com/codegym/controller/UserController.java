package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.model.dto.UpdatePasswordRequest;
import com.codegym.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request,
                                            @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        String newPassword = userDetails.getPassword();

        try {
            userService.changePassword(username, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating password " + e.getMessage());
        }
    }

//        System.out.println("hello");
//        return null;
}
