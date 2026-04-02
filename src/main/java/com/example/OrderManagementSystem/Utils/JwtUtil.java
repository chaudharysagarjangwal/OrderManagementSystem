package com.example.OrderManagementSystem.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    //Generate token
    public String generateToken(String email){
        Map<String,Object> claims=new HashMap<>();
        return generateToken(claims,email);
    }

    private String generateToken(Map<String,Object> claims, String subject) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey()).compact();

    }
    // Get signing key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    //Extract email from token
    public String ExtractEmail(String token){
        return extractClaim(token, Claims::getSubject);

    }
    private Date extractExpiration(String Token) {

        return extractClaim(Token,Claims::getExpiration);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver) {
        final Claims claims=exctractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims exctractAllClaims(String Token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(Token)
                .getPayload();
    }
    public boolean isTokenValid(String Token, String email) {
        final String tokenEmail=ExtractEmail(Token);
        return (tokenEmail.equals(email) && !isTokenExpired(Token));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }


}
