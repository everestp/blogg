package com.offnine.blogg.security;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    // 🔐 Secret Key (Production मा सुरक्षित र लामो राख्नुहोस्)
    private final String SECRET_KEY = "mySuperSecretKey123456jdkjfhkdjfhksdfhksdfhksdjfhsdkfhksdfhksdfhksdfhksdfhisdhfksdhfksdhfkshfsk";

    // ⏳ Expiration Time (1 Hour = 3600000ms)
    private final long EXPIRATION_TIME = 3600000;

    // ✅ JWT Token Generate गर्ने Method
    public String generateToken(UserDetails userDetails) {
         
        try {
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                     .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            throw new RuntimeException("Failed to generate JWT token: " + e.getMessage());
        }
    }

    // ✅ Token बाट Username निकाल्ने Method
    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    // ✅ Token Expired भएको छ कि छैन चेक गर्ने Method
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // ✅ Token Valid छ कि छैन जाँच गर्ने Method
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ✅ Token बाट Claims निकाल्ने Method
    private Claims extractClaims(String token) {
    byte[] secretBytes = SECRET_KEY.getBytes(); // Get bytes from your secret string
    SecretKey key = Keys.hmacShaKeyFor(secretBytes); // Create a SecretKey

    return Jwts.parserBuilder()
            .setSigningKey(key) // Use the SecretKey
            .build()
            .parseClaimsJws(token)
            .getBody();
}
}
