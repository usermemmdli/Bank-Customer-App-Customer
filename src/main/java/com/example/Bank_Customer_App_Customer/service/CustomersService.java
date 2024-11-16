package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dto.request.CustomersRequest;
import com.example.Bank_Customer_App_Customer.mapper.CustomersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final CustomersRepository customersRepository;
    private final CustomersMapper customersMapper;

    public Customers editProfile(String currentUserEmail, CustomersRequest customersRequest) {
        return customersRepository.findByEmail(currentUserEmail)
                .map(customers -> {
                    if (customersRequest.getName() != null) {
                        customers.setName(customersRequest.getName());
                    }
                    if (customersRequest.getSurname() != null) {
                        customers.setSurname(customersRequest.getSurname());
                    }
                    if (customersRequest.getEmail() != null) {
                        customers.setEmail(customersRequest.getEmail());
                    }
                    customers.setUpdatedAt(Timestamp.from(Instant.now()));
                    return customersRepository.save(customers);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
