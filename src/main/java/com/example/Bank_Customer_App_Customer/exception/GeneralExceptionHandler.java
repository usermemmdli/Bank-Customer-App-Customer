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

    @ExceptionHandler({CustomerNotFoundException.class, CardNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundException(RuntimeException ex) {
        Map<String, Object> body = Map.of(
                "error", "Resource not found",
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CardUnauthorizedException.class)
    public ResponseEntity<?> handleCardUnauthorizedException(CardUnauthorizedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Resource not found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
