package com.example.Bank_Customer_App_Customer.dao.repository;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    @EntityGraph(attributePaths = {"customers"})
    Optional<Card> findByCustomersId(Long id);

    @EntityGraph(attributePaths = {"customers"})
    Optional<Card> findByCardNumber(String cardNumber);
}
