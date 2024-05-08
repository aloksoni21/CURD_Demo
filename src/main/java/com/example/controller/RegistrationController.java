package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getName()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Save user to database
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}