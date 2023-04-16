package com.dev.fahmi.controller;

import com.dev.fahmi.domain.User;
import com.dev.fahmi.dto.LoginRequest;
import com.dev.fahmi.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // load the user details from the database
            Optional<User> user = userDetailsService.findByUsername(loginRequest.getUsername());

            User userDetails = user.get();
            // check if the username and password are valid
            if (!userDetails.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            // return a success response if the username and password are valid
            return ResponseEntity.ok().build();

        } catch (UsernameNotFoundException ex) {
            // return a failure response if the username is not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }



}
