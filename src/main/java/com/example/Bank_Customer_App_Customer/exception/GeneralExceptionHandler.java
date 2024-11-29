package com.example.Bank_Customer_App_Customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Resource not found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CardNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Resource not found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
