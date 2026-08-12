package com.traders.nst.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${spring.application.jwt.secret}")
    private String secret;
    
    @Autowired
    transient HttpServletRequest request;

    public String generateToken(String uuid, String userName ) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("UUID",uuid);
        return doGenerateToken(claims,userName);
    }

    public String doGenerateToken(Map<String,Object> claims,String userName) {
     return Jwts.builder().setClaims(claims).setSubject(userName).signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    
    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


}
