package com.flightBookingSys.central_api.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthUtility {

    @Value("${jwt.expiration.time}")
    long jwtExpirationTime;

    @Value("${jwt.secret.password}")
    String securePassword;
    
    public String generateToken(String email, String password , String role){
        String payload  = email + ":" + password + ":" + role;
        String answer = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 + jwtExpirationTime)) // 10 hours expiration
            .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, securePassword)
            .setSubject(payload)
            .compact();
        return answer;
    }

    // decrypts the token using securePassword
    public String decryptToken(String token){
        String payload = Jwts.parser().setSigningKey(securePassword)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return payload;
    }
}
