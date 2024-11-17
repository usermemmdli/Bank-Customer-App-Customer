package com.example.Bank_Customer_App_Customer.controller;

import com.example.Bank_Customer_App_Customer.service.AuthService;
import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dto.request.LoginRequest;
import com.example.Bank_Customer_App_Customer.dto.request.SignUpRequest;
import com.example.Bank_Customer_App_Customer.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpRequest signUpRequest) {
        Customers customers = authService.registerCustomer(signUpRequest);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCustomer(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

}
