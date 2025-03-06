package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dto.request.LoginRequest;
import com.example.Bank_Customer_App_Customer.dto.request.SignUpRequest;
import com.example.Bank_Customer_App_Customer.dto.response.JwtResponse;
import com.example.Bank_Customer_App_Customer.exception.CustomerLoginError;
import com.example.Bank_Customer_App_Customer.exception.CustomerNotFoundException;
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

    public void registerCustomer(SignUpRequest signUpRequest) {
        if (customersRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
        Customers customers = new Customers();
        customers.setName(signUpRequest.getName());
        customers.setSurname(signUpRequest.getSurname());
        customers.setEmail(signUpRequest.getEmail());
        customers.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        customers.setIsActive(true);
        customers.setCreatedAt(Timestamp.from(Instant.now()));
        customersRepository.save(customers);
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
                .orElseThrow(() -> new CustomerLoginError("Invalid email or password"));
        if (!customer.getIsActive()) {
            throw new CustomerNotFoundException("Customer is not active");
        }
        String accessToken = jwtService.createAccessToken(customer);
        String refreshToken = jwtService.createRefreshToken(customer);

        return new JwtResponse(accessToken, refreshToken);
    }
}
