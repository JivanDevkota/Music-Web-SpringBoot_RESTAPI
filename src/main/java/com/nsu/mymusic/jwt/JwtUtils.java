package com.nsu.mymusic.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final String SECRET_KEY="413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    private final long JWT_EXPIRATION_TIME = 1000*10*60*60;

    private final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;


    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap();
        return createRefreshToken(claims,username);
    }

    private String createRefreshToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap();
        return createRefreshToken(claims,username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    private static Key getSignKey(){
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUsernameFromToken(String token){
        String username = extractClaims(token, Claims::getSubject);
        return username;
    }

    private <T>T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();
    }

    public Date getExpirationDateFromToken(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        final Date isExpired= getExpirationDateFromToken(token);
        return isExpired.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
    }
}
