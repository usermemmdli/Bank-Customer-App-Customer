package com.example.Bank_Customer_App_Customer.dto.request;

import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class TransactionRequest {
    private String description;
    private String senderCardNumber;
    private String receiverCardNumber;
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private Long customersId;
    private String status;
    private Double amount;
}
