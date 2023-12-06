package com.example.demo.Services;

import com.example.demo.Enities.UserEnity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String KEY_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public  String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    private String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 24 * 60))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256) // mã kiểu header và signature để cho ra 1 mã hoàn chỉnh
                .compact();
    }

    private boolean isTokenExpired(String token){return extractExpiration(token).before(new Date());}
    private Date extractExpiration(String token){return  extractClaim(token,Claims::getExpiration);}

    private <T> T extractClaim(String token, Function<Claims,T> clamisResolver){
        final Claims claims = extractAllClaim(token);
        return clamisResolver.apply(claims);
    }
    private Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(KEY_SECRET);
        return  Keys.hmacShaKeyFor(keyBytes);
    }

}

