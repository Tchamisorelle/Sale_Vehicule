package com.Sale_Vehicule.service_authentification.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Sale_Vehicule.service_authentification.dto.Costumer;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class Utils {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(Costumer user) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);

        

        JwtBuilder builder = Jwts.builder()
            .setSubject(user.getEmail())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key)
            .claim("password", user.getPassword())
            .claim("name", user.getName())
            .claim("role", user.getRole());

        return builder.compact();
    }
}
