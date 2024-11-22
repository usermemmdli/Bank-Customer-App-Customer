package com.example.Bank_Customer_App_Customer.dto.request;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    private String description;
    private String senderCardNumber;
    private String receiverCardNumber;
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private Long customersId;
    private String status;
    private Double amount;
}
