package com.example.Bank_Customer_App_Customer.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardRequest {
    private String name;
    private String cardNumber;
    private String pin;
    private Double balance;
    private Boolean isActive;
    private Timestamp updatedAt;
}
