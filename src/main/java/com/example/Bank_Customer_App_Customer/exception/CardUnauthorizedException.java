package com.example.Bank_Customer_App_Customer.exception;

public class CardUnauthorizedException extends RuntimeException {
    public CardUnauthorizedException(String message) {
        super(message);
    }
}
