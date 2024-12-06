package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CardResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class CardMapper {
    public static CardResponse cardToCardResponse(Card card) {
        return CardResponse.builder()
                .name(card.getName())
                .cardNumber(card.getCardNumber())
                .pin(card.getPin())
                .ccyCode(card.getCcyCode())
                .holderName(card.getHolderName())
                .balance(card.getBalance())
                .isActive(card.getIsActive())
                .expireDate(card.getExpireDate())
                .createdAt(card.getCreatedAt())
                .build();
    }

    public static Card cardRequestToCard(CardRequest cardRequest) {
        return Card.builder()
                .name(cardRequest.getName())
                .cardNumber(cardRequest.getCardNumber())
                .balance(cardRequest.getBalance())
                .pin(cardRequest.getPin())
                .isActive(cardRequest.getIsActive())
                .updatedAt(cardRequest.getUpdatedAt())
                .build();
    }
}
