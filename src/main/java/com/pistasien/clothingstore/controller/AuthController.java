package com.pistasien.clothingstore.controller;

import com.pistasien.clothingstore.dto.*;
import com.pistasien.clothingstore.service.AuthService;
import com.pistasien.clothingstore.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;

    public AuthController(AuthService service, OtpService otpService) {
        this.authService = service;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public RegisterResponseDTO create(@Valid @RequestBody RegisterRequestDTO request){
        return authService.createUser(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequest){
        return authService.loginService(loginRequest);
    }

    @PostMapping("/sentOtp")
    public OtpRequestResponseDTO otpRequest(@Valid @RequestBody OtpRequestDTO otpRequest){
        return otpService.requestOtp(otpRequest);
    }

    @PostMapping("/verifyOtp")
    public VerifyOtpResponseDTO verifyOtp(@RequestBody VerifyOtpRequestDTO verifyRequest){
        return otpService.verify(verifyRequest);
    }
}


