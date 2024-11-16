package com.example.Bank_Customer_App_Customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
