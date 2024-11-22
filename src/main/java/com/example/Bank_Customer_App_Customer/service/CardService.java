package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CardRepository;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CardResponse;
import com.example.Bank_Customer_App_Customer.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CustomersRepository customersRepository;

    public List<CardResponse> getUserCards(String currentUserEmail) {
        Customers customer = customersRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Optional<Card> cards = cardRepository.findByCustomersId(customer.getId());

        return cards.stream()
                .map(CardMapper::cardToCardResponse)
                .toList();
    }

//    public ResponseEntity<?> editCard(String currentUserEmail, CardRequest cardRequest) {
//        Customers customer = customersRepository.findByEmail(currentUserEmail)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        List<Card> cards = cardRepository.findByCustomersId(customer.getId());
//
//        if (cardRepository.findByCardNumber(cardRequest.getCardNumber())
//    }

}
