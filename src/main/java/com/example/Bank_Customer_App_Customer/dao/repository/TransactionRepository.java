package com.example.Bank_Customer_App_Customer.dao.repository;

import com.example.Bank_Customer_App_Customer.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
