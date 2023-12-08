package com.example.keycloak3.controller;

import com.example.keycloak3.dto.UserDTO;
import com.example.keycloak3.exception.ApiRequestException;
import com.example.keycloak3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    @GetMapping (value = "/admin")
    public ResponseEntity<?> keycloakDemo2() {
        return ResponseEntity.ok("Ta là administrator" );
    }

    @GetMapping (value = "/manager")
    public ResponseEntity<String> keycloakDemo1() {
        return ResponseEntity.ok("Ta là manager");
    }

    @GetMapping (value = "/staff")
    public ResponseEntity<String> keycloakDemo3() {
        return ResponseEntity.ok("Ta là staff");
    }

    @GetMapping(value = "/admin/demo")
    public ResponseEntity<?> keycloakDemo4() {
        throw new ApiRequestException("Access denied !!!");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
