package com.ethan.worldwide.security.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhenghui
 * @Description JWT Token工具类
 * @Date 2022/7/3
 */
public class JwtTokenUtils {

    private static final String secret = "admin";
    private static final int expiration = 604800;

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date(System.nanoTime()));
        return generateToken(claims);
    }

    public static String getUserNameFromToken(String authToken) {
        Claims claimsFromToken = getClaimsFromToken(authToken);
        return claimsFromToken.getSubject();
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    private static Date generateExpirationDate() {
        return new Date(System.nanoTime() + expiration * 1000);
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }
}
