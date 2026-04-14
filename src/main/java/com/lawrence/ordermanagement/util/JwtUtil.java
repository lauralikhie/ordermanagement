package com.lawrence.ordermanagement.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KRY = "ajsbhdhabwkljh kj haekjh kaj hsdkjh";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KRY.getBytes());


    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email).issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

}
