package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.entity.Transaction;
import com.example.Bank_Customer_App_Customer.dao.repository.CardRepository;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dao.repository.TransactionRepository;
import com.example.Bank_Customer_App_Customer.dto.request.TransactionRequest;
import com.example.Bank_Customer_App_Customer.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CustomersRepository customersRepository;
    private final TransactionMapper transactionMapper;

    public ResponseEntity<TransactionRequest> createTransaction(String currentUserEmail, TransactionRequest transactionRequest) {
        Customers customer = customersRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Optional<Card> cardSender = cardRepository.findByCustomersId(customer.getId());


        if (transactionRequest.getDescription() == null
                || transactionRequest.getSenderCardNumber() == null
                || transactionRequest.getReceiverCardNumber() == null
                || transactionRequest.getCustomersId() == null
                || transactionRequest.getStatus() == null
                || transactionRequest.getAmount() < 0) {
            throw new RuntimeException("Invalid transaction request");
        }


        Transaction transaction = TransactionMapper.transactionRequestToTransaction(transactionRequest);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionRequest);
    }
}
