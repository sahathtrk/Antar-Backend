package com.andree.antar_be.utils;

import com.andree.antar_be.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Jacksonized
@Component
@NoArgsConstructor
public class JWTToken {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired}")
    private int expired;

    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    @Value("${jwt.refresh_expired}")
    private int refreshExpired;

    public Map<String, Object> generateToken(User user) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("expired", new Date(System.currentTimeMillis() + expired));
        temp.put("token", getToken(user, expired, secret));
        return temp;
    }
    public String generateRefreshToken(User user){
        return getToken(user, refreshExpired, refreshSecret);
    }

    private String getToken(User user, int expired, String refreshSecret) {
        Claims claims = Jwts.claims();
        claims.put("ID", user.getId());
        claims.put("ROLE", user.getRole());
        return Jwts.builder().setClaims(claims).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + expired)).
                signWith(SignatureAlgorithm.HS256, refreshSecret).
                compact();
    }
}
