package com.example.projetSoutenance2026PME.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService     {
    private final SecretKey secretKey;
    private final long expiration;

    public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        this.expiration = expiration;
    }

    public String genererToken(String username){
        Date maintenant = new Date();
        Date expirationDate = new Date(
                maintenant.getTime() + expiration
        );

        return Jwts.builder()
                .subject(username)
                .issuedAt(maintenant)
                .expiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public String extraireUsername(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean verify(String token){
        try{
            extraireUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
