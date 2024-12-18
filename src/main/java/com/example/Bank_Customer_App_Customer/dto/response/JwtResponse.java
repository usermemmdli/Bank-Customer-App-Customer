package com.example.Bank_Customer_App_Customer.dto.response;

import lombok.Getter;

@Getter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;

    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}