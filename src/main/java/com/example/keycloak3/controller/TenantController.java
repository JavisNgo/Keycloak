package com.example.keycloak3.controller;

import com.example.keycloak3.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    JwtService jwtService;

    @GetMapping(value = "/demo")
    public ResponseEntity<?> keycloakDemo4(@NonNull HttpServletRequest request) {
        boolean flag = jwtService.isTenant(request);
        if(!flag) {
            return ResponseEntity.badRequest().body("Access denied in");
        }
        return ResponseEntity.ok("Access accept in");
    }
}
