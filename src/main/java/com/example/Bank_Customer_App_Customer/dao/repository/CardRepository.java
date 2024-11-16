package com.example.Bank_Customer_App_Customer.dao.repository;

import com.example.Bank_Customer_App_Customer.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Card, Long> {
}
