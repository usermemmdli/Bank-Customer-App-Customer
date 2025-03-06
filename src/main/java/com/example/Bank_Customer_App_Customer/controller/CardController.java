package com.example.Bank_Customer_App_Customer.controller;

import com.example.Bank_Customer_App_Customer.dto.request.CardRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CardResponse;
import com.example.Bank_Customer_App_Customer.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<CardResponse>> getUserCards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        List<CardResponse> userCards = cardService.getUserCards(currentUserEmail);
        return ResponseEntity.ok(userCards);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/edit-card")
    public ResponseEntity<CardResponse> editCard(@Valid @RequestBody CardRequest cardRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        CardResponse editedCard = cardService.editCard(currentUserEmail, cardRequest);
        return ResponseEntity.ok(editedCard);
    }
}
