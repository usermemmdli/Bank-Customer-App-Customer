package com.example.Bank_Customer_App_Customer.exception;

public class CardNotActiveException extends RuntimeException {
    public CardNotActiveException(String message) {
        super(message);
    }
}
