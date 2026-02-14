package com.example.Microservices.Service;

import com.example.Microservices.Entity.JwtParserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter {
    public String secret = "9f3c2a1b4e8d7f9c6a2b3c4d5e6f7a8b9c0d1e2f3a4b5c6d7e8f9a0b1c2";

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    public String jwtToken(String email, String role) {
        return Jwts.builder().subject(email).claim("role", role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 860000000L)).signWith(this.getKey(), SIG.HS256).compact();
    }

    public JwtParserResponse getUsername(String token) {
        Claims claims = (Claims)Jwts.parser().setSigningKey(this.getKey()).build().parseSignedClaims(token).getBody();
        String role = (String)claims.get("role", String.class);
        String username = claims.getSubject();
        return new JwtParserResponse(username, role);
    }
}
