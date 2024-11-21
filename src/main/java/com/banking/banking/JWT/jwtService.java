package com.banking.banking.JWT;

import com.banking.banking.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
@Service
public class jwtService {

    @Value("${secret-jwt.expiration.minutes}")
    private  long EXPIRATION_MINUTES;
    @Value("${Secret_key}")
    private String secretKey;



    public String generateToken(AppUser user, Map<String, Object> extraClaims) {


        Date issuedDate = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(issuedDate.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return     Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDate)
                .signWith(generateSecretKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key generateSecretKey() {
        byte [] secret= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secret);

    }

    public String extractUsername(String jwt) {
        return  extractAllClaims(jwt).getSubject();

    }

    private Claims extractAllClaims(String jwt){
        return  Jwts.parserBuilder().setSigningKey(generateSecretKey()).build()
                .parseClaimsJws(jwt).getBody();

    }
    }

