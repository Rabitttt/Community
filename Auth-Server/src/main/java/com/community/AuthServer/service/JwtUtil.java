package com.community.AuthServer.service;

import com.community.AuthServer.model.RequestUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generate(RequestUser requestUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", requestUser.getId());
        return doGenerateToken(claims,requestUser.getUserName());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        long expirationTimeLong;

        final Date createdDate = new Date();

        expirationTimeLong = 2 * 1000 * 60 * 60 * 24; //2 day

        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

}
