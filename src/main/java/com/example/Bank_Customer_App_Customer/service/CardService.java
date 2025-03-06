package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CardRepository;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CardResponse;
import com.example.Bank_Customer_App_Customer.exception.CardNotFoundException;
import com.example.Bank_Customer_App_Customer.exception.CustomerNotFoundException;
import com.example.Bank_Customer_App_Customer.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CustomersRepository customersRepository;

    public List<CardResponse> getUserCards(String currentUserEmail) {
        Customers customer = customersRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Optional<Card> cards = cardRepository.findByCustomersId(customer.getId());

        return cards.stream()
                .map(cardMapper::cardToCardResponse)
                .toList();
    }

    public CardResponse editCard(String currentUserEmail, CardRequest cardRequest) {
        Customers customer = customersRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Card editedCard = cardRepository.findByCardNumber(cardRequest.getCardNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found"));

        if (cardRequest.getName() != null) {
            editedCard.setName(cardRequest.getName());
        }
        if (cardRequest.getPin() != null) {
            editedCard.setPin(cardRequest.getPin());
        }
        if (cardRequest.getIsActive() != null) {
            editedCard.setIsActive(cardRequest.getIsActive());
        }
        editedCard.setUpdatedAt(Timestamp.from(Instant.now()));
        cardRepository.save(editedCard);

        return cardMapper.cardToCardResponse(editedCard);
    }
}
