package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dto.request.CustomersRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CustomersResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@Mapper(componentModel = "spring")
public class CustomersMapper {
    public CustomersResponse customersToCustomersResponse(Customers customers) {
        return CustomersResponse.builder()
                .id(customers.getId())
                .name(customers.getName())
                .surname(customers.getSurname())
                .email(customers.getEmail())
                .isActive(customers.getIsActive())
                .createdAt(customers.getCreatedAt())
                .updatedAt(customers.getUpdatedAt())
                .build();
    }

    public Customers customersRequestToCustomers(CustomersRequest customersRequest) {
        return Customers.builder()
                .name(customersRequest.getName())
                .surname(customersRequest.getSurname())
                .email(customersRequest.getEmail())
                .password(customersRequest.getPassword())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}
