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
public class CardResponse {
    private String name;
    private String cardNumber;
    private String pin;
    private String ccyCode;
    private String holderName;
    private Double balance;
    private Boolean isActive;
    private Timestamp expireDate;
    private Timestamp createdAt;
}
