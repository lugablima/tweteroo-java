package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.User;
import com.tweteroo.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sign-up")
public class AuthController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> listAllUsers() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody @Valid UserDTO userDTO) {
        var user = service.save(userDTO);

        if(user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already registered.");
        }
    }
}
