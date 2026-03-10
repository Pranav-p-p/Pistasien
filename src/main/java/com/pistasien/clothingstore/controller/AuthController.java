package com.pistasien.clothingstore.controller;

import com.pistasien.clothingstore.dto.LoginRequestDTO;
import com.pistasien.clothingstore.dto.LoginResponseDTO;
import com.pistasien.clothingstore.dto.RegisterRequestDTO;
import com.pistasien.clothingstore.dto.RegisterResponseDTO;
import com.pistasien.clothingstore.entity.User;
import com.pistasien.clothingstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService service) {
        this.authService = service;
    }

    @PostMapping("/register")
    public RegisterResponseDTO create(@Valid @RequestBody RegisterRequestDTO request){
        return authService.createUser(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequest){
        return authService.loginService(loginRequest);
    }
}


