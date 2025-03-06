package com.example.Bank_Customer_App_Customer.controller;

import com.example.Bank_Customer_App_Customer.service.AuthService;
import com.example.Bank_Customer_App_Customer.dto.request.LoginRequest;
import com.example.Bank_Customer_App_Customer.dto.request.SignUpRequest;
import com.example.Bank_Customer_App_Customer.dto.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestBody SignUpRequest signUpRequest) {
        authService.registerCustomer(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCustomer(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
