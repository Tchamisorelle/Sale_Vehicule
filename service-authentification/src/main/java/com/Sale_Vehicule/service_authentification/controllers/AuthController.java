package com.Sale_Vehicule.service_authentification.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sale_Vehicule.service_authentification.dto.LoginRequest;
import com.Sale_Vehicule.service_authentification.services.AuthService;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/check-password")
    public boolean checkPassword(@RequestBody LoginRequest checking) {
        return authService.checkPassword(checking.getEmail(), checking.getPassword());
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getUserDetails(Principal principal) {
        return ResponseEntity.ok("Utilisateur connecté : " + principal.getName());
    }
    
}
