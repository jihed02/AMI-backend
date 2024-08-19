package com.example.amibackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service("jwtservice")
public class JwtService {
    private static final String secretKey="e2b843d3ce763c4a25cd9472be975108124cf6e24c444b3c7b4afd7c17eebb1d210b5b8d6be73b0b4dad7bc93828081de7d5d92d61224c302f231c2c977c29d6d2efa598e890589032ae7fb3c2fb45005d501ae307e4a90d1178d09e1b018439e1a4cec1d5069934bd05133237a39a90347b08f0db05f2cfc50793320388f2716db1af78af25e377b1ee1102cb6b93c19e4f501ccd868c50529ff0dcef520d800b716644ff8ebfb71b1528769bacbbc0f23a63b434a2c290318c421430f0e3adc989ac8b74fca9a8d7a7788283d89c63565a8484dc15c490ebc848ab2bb2d6db5d541ed9db31a4112f226f1327125a3b98ace8b583d84d1f20dcc599f40dbee7";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extracAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extracAllClaims(String token){

    return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24*10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey() {
        byte[] KeyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
}
