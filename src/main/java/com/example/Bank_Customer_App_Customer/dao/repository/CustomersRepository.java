package com.example.Bank_Customer_App_Customer.dao.repository;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

    boolean existsByEmail(String email);

    Optional<Customers> findByEmail(String email);
}
