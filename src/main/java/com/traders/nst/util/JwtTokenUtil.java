package com.traders.nst.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
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
     return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+3600000)).signWith(SignatureAlgorithm.HS256,secret).compact();

    }

    
    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        Date expiryTime = getExpiryFromToken(token);
        return expiryTime.before(new Date());
    }

    public Date getExpiryFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
}
