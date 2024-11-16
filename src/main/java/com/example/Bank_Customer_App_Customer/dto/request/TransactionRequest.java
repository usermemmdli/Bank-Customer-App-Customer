package com.example.Bank_Customer_App_Customer.dto.request;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequest {
    private String description;
    private String senderCardNumber;
    private String receiverCardNumber;
    private String status;
    private Double amount;
}
