package com.example.Bank_Customer_App_Customer.controller;

import com.example.Bank_Customer_App_Customer.dto.request.CustomersRequest;
import com.example.Bank_Customer_App_Customer.dto.response.CustomersResponse;
import com.example.Bank_Customer_App_Customer.service.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomersController {
    private final CustomersService customersService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/edit-profile")
    public ResponseEntity<CustomersResponse> editProfile(@Valid @RequestBody CustomersRequest customersRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        CustomersResponse editedCustomer = customersService.editProfile(currentUserEmail, customersRequest);
        return ResponseEntity.ok(editedCustomer);
    }
}
