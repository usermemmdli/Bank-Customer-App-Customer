package com.example.Bank_Customer_App_Customer.service;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private String SECRET_KEY = String.valueOf(generateKey());

    //run eleyende error verib 256 bitlik key isteyirdi deye bele bir method
    public static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); //keyin olcusunu veririk
        SecretKey secretKey = keyGen.generateKey(); //keyin generate olunmasi
        //keyi Base64 formatina cevirir
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000; // 15 deqiqe
    private final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 gun

    public JwtService() throws Exception {
    }

    public String createAccessToken(Customers customers) {
        return Jwts.builder()
                .setSubject(customers.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRefreshToken(Customers customers) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) customers.getAuthorities();
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", roles);

        return Jwts.builder()
                .setSubject(customers.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, Customers customers) {
        String email = extractEmail(token);
        return email.equals(customers.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
