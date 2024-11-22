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

        Card senderCard = cardRepository.findByCardNumber(transactionRequest.getSenderCardNumber())
                .orElseThrow(() -> new RuntimeException("Sender card not found"));

        if (!senderCard.getCustomers().equals(customer)) {
            throw new RuntimeException("Unauthorized sender card");
        }

        Card receiverCard = cardRepository.findByCardNumber(transactionRequest.getReceiverCardNumber())
                .orElseThrow(() -> new RuntimeException("Receiver card not found"));

        if (transactionRequest.getAmount() == null || transactionRequest.getAmount() <= 0) {
            throw new RuntimeException("Invalid amount for transaction");
        }
        if (senderCard.getBalance() < transactionRequest.getAmount()) {
            throw new RuntimeException("Insufficient balance for transaction");
        }

        senderCard.setBalance(senderCard.getBalance() - transactionRequest.getAmount());
        receiverCard.setBalance(receiverCard.getBalance() + transactionRequest.getAmount());

        cardRepository.save(senderCard);
        cardRepository.save(receiverCard);

        Transaction transaction = TransactionMapper.transactionRequestToTransaction(transactionRequest);
        transaction.setSenderCardNumber(senderCard.getCardNumber());
        transaction.setReceiverCardNumber(receiverCard.getCardNumber());
        transaction.setDescription("send money");
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setStatus("completed");
        transaction.setCustomers(customer);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionRequest);
    }
}
