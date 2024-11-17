package com.example.Bank_Customer_App_Customer.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Table(name = "customers")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "customers",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private List<Card> cardList;
    @JsonIgnore
    @OneToMany(mappedBy = "customers",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    private List<Transaction> transactionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return "";
    }
}
