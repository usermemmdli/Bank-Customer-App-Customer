package com.example.Bank_Customer_App_Customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private String description;
    private String senderCardNumber;
    private String receiverCardNumber;
    private String status;
    private Double amount;
}
