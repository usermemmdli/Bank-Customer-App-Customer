package com.example.Bank_Customer_App_Customer.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Builder
@Table(name = "transactions")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "sender_card_number")
    private String senderCardNumber;
    @Column(name = "receiver_card_number")
    private String receiverCardNumber;
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Customers customers;
    private String status;
    private Double amount;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
