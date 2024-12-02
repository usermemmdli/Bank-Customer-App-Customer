package com.example.Bank_Customer_App_Customer.controller;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import com.example.Bank_Customer_App_Customer.dao.repository.CardRepository;
import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CardResponse;
import com.example.Bank_Customer_App_Customer.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user-cards")
    public ResponseEntity<?> getUserCards() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = authentication.getName();

            List<CardResponse> userCards = cardService.getUserCards(currentUserEmail);
            return ResponseEntity.ok(userCards);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cards not found");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/edit-card")
    public ResponseEntity<?> editCard(@Valid @RequestBody CardRequest cardRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = authentication.getName();

            ResponseEntity<?> editedCard = cardService.editCard(currentUserEmail, cardRequest);
            return ResponseEntity.ok(editedCard);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
