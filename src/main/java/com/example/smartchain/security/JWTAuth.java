package com.example.smartchain.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTAuth {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // >=32 chars
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 🔐 Generate Token with Role
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extract Role
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // 🔍 Extract Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 🔍 Validate Token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 🔍 Check Expiry
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 🔍 Extract Claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}