package com.ecommerce.amazio.security.jwt;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.access.expiration.time}")
    long expirationTime;

    @Value("${jwt.access.secret.password}")
    String secretPassword;

    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // email is subject
                .claim("role", user.getUserRole().name()) // user role : "ADMIN" or "CUSTOMER"
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretPassword)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretPassword)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretPassword)
                .parseClaimsJws(token).
                getBody().
                getSubject();
    }

    public UserRole extractRole(String token) {
        String role=Jwts.parser()
                .setSigningKey(secretPassword)
                .parseClaimsJws(token)
                .getBody()
                .get("role")
                .toString();
        return UserRole.valueOf(role);
    }
}
