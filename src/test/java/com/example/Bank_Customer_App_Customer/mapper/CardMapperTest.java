package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import org.junit.jupiter.api.Test;

public class CardMapperTest {
    @Test
    void toCardResponse() {
        //Arrange
        var request = new Card();
        request.setName("birbank");
        request.setCardNumber("1234567890");
        request.setPin("1234");
        request.setCcyCode("344");
        request.setHolderName("Mustafa");

        //Act(actual)
        var actual = CardMapper.cardToCardResponse(request);

        //Assert
        assert actual.getName().equals("birbank");
        assert actual.getCardNumber().equals("1234567890");
        assert actual.getPin().equals("1234");
        assert actual.getCcyCode().equals("344");
        assert actual.getHolderName().equals("Mustafa");
    }

    @Test
    void toCardEntity() {
        //Arrange
        var request = new CardRequest();
        request.setName("birbank");
        request.setCardNumber("1234567890");
        request.setPin("1234");
        request.setBalance(123.0);
        request.setIsActive(true);

        //Act(actual)
        var actual = CardMapper.cardRequestToCard(request);

        //Assert
        assert actual.getName().equals("birbank");
        assert actual.getCardNumber().equals("1234567890");
        assert actual.getPin().equals("1234");
        assert actual.getBalance() == 123.0;
        assert actual.getIsActive() == true;
    }
}
