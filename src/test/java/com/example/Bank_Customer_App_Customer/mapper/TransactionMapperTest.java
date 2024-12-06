package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Transaction;
import com.example.Bank_Customer_App_Customer.dto.request.TransactionRequest;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {
    @Test
    void toTransactionResponse() {
        //Arrange
        var request = new Transaction();
        request.setDescription("description");
        request.setSenderCardNumber("1234567890");
        request.setReceiverCardNumber("1234567891");
        request.setStatus("status");
        request.setAmount(123.0);

        //Act(actual)
        var actual = TransactionMapper.transactionToTransactionResponse(request);

        //Assert
        assert actual.getDescription().equals("description");
        assert actual.getSenderCardNumber().equals("1234567890");
        assert actual.getReceiverCardNumber().equals("1234567891");
        assert actual.getStatus().equals("status");
        assert actual.getAmount().equals(123.0);
    }

    @Test
    void toTransactionEntity() {
        //Arrange
        var request = new TransactionRequest();
        request.setDescription("description");
        request.setSenderCardNumber("1234567890");
        request.setReceiverCardNumber("1234567891");
        request.setStatus("status");
        request.setAmount(123.0);

        //Act(actual)
        var actual = TransactionMapper.transactionRequestToTransaction(request);

        //Assert
        assert actual.getDescription().equals("description");
        assert actual.getSenderCardNumber().equals("1234567890");
        assert actual.getReceiverCardNumber().equals("1234567891");
        assert actual.getStatus().equals("status");
        assert actual.getAmount().equals(123.0);
    }
}
