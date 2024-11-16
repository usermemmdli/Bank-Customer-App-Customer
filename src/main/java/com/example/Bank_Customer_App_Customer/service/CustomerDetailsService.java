package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dao.repository.CustomersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private final CustomersRepository customersRepository;

    public CustomerDetailsService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customers customer = customersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.withUsername(customer.getEmail())
                .password(customer.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}
