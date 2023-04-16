package com.dev.fahmi.controller;

import com.dev.fahmi.common.JwtUtil;
import com.dev.fahmi.domain.User;
import com.dev.fahmi.dto.JwtResponse;
import com.dev.fahmi.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class JwtController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/generateJwt")
    public ResponseEntity<?> generateJwt() {
        String token = jwtUtil.getToken("admin");
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
