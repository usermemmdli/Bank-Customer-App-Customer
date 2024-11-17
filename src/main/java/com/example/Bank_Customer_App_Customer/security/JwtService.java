package com.example.Bank_Customer_App_Customer.security;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtService {

//    private final String SECRET_KEY = String.valueOf(generateKey());
//
//    //run eleyende error verib 256 bitlik key isteyirdi deye bele bir method
//    public static String generateKey() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256); //keyin olcusunu veririk
//        SecretKey secretKey = keyGen.generateKey(); //keyin generate olunmasi
//        //keyi Base64 formatina cevirir
//        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//    }

    private final String SECRET_KEY = "u8PzZ3sGx2C7dF1Vq4LmNoE8TyJwRaUkKjAiorCBHRXOcnwoI";
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

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)  // Secret key ile doğrulama yapıyoruz
                    .parseClaimsJws(token)     // Token'ı parse edip claim'leri alıyoruz
                    .getBody();                // Claims'i döndürüyoruz

        } catch (SignatureException e) {
            throw new RuntimeException("JWT signature does not match locally computed signature.");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT is expired.");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("JWT token is unsupported.");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("JWT token is malformed.");
        } catch (Exception e) {
            throw new RuntimeException("JWT validation failed.");
        }
    }
}
