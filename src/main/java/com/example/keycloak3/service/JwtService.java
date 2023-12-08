package com.example.keycloak3.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtService {
    static String[] tenantClients = {"Hitech", "Nashtech"} ;

    public boolean isTenant(@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Access denied.");
        }
        String token = authHeader.substring(7);
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String tenantID = claims.get("tenantID").asString();
        for (String t : tenantClients) {
            if(tenantID.equalsIgnoreCase(t)) {
                return true;
            }
        }
        return false;
    }

    public String extractUserEmail(@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Access denied.");
        }
        String token = authHeader.substring(7);
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String result = claims.get("email").asString();
        if(result.isEmpty()) {
            throw new AccessDeniedException("Not found in token");
        }
        return result;
    }
}
