package com.example.Bank_Customer_App_Customer.dto.request;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Builder
public class CustomersRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Timestamp updatedAt;
}
