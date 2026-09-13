package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.security.LoginRequest;
import com.example.projetSoutenance2026PME.dto.security.LoginResponse;
import com.example.projetSoutenance2026PME.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response  = authService.authentifier(request);
        return ResponseEntity.ok(response);
    }
}
