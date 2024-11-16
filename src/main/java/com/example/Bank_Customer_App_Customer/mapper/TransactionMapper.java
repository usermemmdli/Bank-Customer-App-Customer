package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Transaction;
import com.example.Bank_Customer_App_Customer.dto.request.TransactionRequest;
import com.example.Bank_Customer_App_Customer.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class TransactionMapper {
    public static TransactionResponse transactionToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .senderCardNumber(transaction.getSenderCardNumber())
                .receiverCardNumber(transaction.getReceiverCardNumber())
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .build();
    }

    public static Transaction transactionRequestToTransaction(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .description(transactionRequest.getDescription())
                .senderCardNumber(transactionRequest.getSenderCardNumber())
                .receiverCardNumber(transactionRequest.getReceiverCardNumber())
                .status(transactionRequest.getStatus())
                .amount(transactionRequest.getAmount())
                .build();
    }
}
