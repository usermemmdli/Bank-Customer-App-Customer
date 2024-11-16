package com.example.Bank_Customer_App_Customer.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Table(name = "card")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "card_number")
    private String cardNumber;
    private String pin;
    @Column(name = "ccy_code")
    private String ccyCode;
    @Column(name = "holder_name")
    private String holderName;
    private Double balance;
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Customers customers;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "expire_date")
    private Timestamp expireDate;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
