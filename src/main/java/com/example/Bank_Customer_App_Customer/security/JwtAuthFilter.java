package com.example.Bank_Customer_App_Customer.security;

import com.example.Bank_Customer_App_Customer.service.CustomerDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomerDetailsService customerDetailsService;

    public JwtAuthFilter(JwtService jwtService, CustomerDetailsService customerDetailsService) {
        this.jwtService = jwtService;
        this.customerDetailsService = customerDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization header'ını al
        String token = request.getHeader("Authorization");

        // Eğer token varsa ve "Bearer " ile başlıyorsa
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // "Bearer " kısmını çıkar

            try {
                Claims claims = jwtService.validateToken(token);

                String email = claims.getSubject();
                UserDetails userDetails = customerDetailsService.loadUserByUsername(email);

                if (email != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
