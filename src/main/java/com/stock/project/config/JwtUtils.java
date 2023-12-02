package com.stock.project.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

public class JwtUtils {
    private static final String SECRET_KEY = "ab1f9c6d58e23a541b7d89e2a0c3f8d04e76a38b572c91fd2f517945cc6b0e50932a5e36a3b7c98b2d2b21e2e0fc88d7c34b924d6632a05a3a71f8603a051b4d";
    public static String generateToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
    private static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null) {
            return bearerToken;
        }
        return null;
    }
}
