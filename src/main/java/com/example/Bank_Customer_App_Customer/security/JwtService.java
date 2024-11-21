package com.example.Bank_Customer_App_Customer.security;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtService {
    private final String SECRET_KEY = "dThQelozc0d4MkM3ZEYxVnE0TG1Ob0U4VHlKd1JhVWtLakFpb3JDQkhSWE9jbndvSQ==";
    private final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000; // 15 deqiqe
    private final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 gun

    public String createAccessToken(Customers customers) {
        return Jwts.builder()
                .setSubject(customers.getEmail())
                .claim("roles", customers.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRefreshToken(Customers customers) {
        return Jwts.builder()
                .setSubject(customers.getEmail())
                .claim("roles", customers.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token expired");
            throw e;
        } catch (JwtException e) {
            log.error("Invalid token", e);
            throw e;
        }
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
