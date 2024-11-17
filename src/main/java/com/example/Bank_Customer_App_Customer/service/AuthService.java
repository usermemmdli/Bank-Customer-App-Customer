package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dto.request.LoginRequest;
import com.example.Bank_Customer_App_Customer.dto.request.SignUpRequest;
import com.example.Bank_Customer_App_Customer.dto.response.JwtResponse;
import com.example.Bank_Customer_App_Customer.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomersRepository customersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Customers registerCustomer(SignUpRequest signUpRequest) {
        if (customersRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
        Customers customer = new Customers();
        customer.setName(signUpRequest.getName());
        customer.setSurname(signUpRequest.getSurname());
        customer.setEmail(signUpRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        customer.setIsActive(true);
        customer.setCreatedAt(Timestamp.from(Instant.now()));

        return customersRepository.save(customer);
    }

    public JwtResponse loginCustomer(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customers customer = customersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        String accessToken = jwtService.createAccessToken(customer);
        String refreshToken = jwtService.createRefreshToken(customer);

        return new JwtResponse(accessToken, refreshToken);
    }
}
