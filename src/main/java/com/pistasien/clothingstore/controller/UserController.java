package com.pistasien.clothingstore.controller;

import com.pistasien.clothingstore.dto.LoginResponseDTO;
import com.pistasien.clothingstore.entity.User;
import com.pistasien.clothingstore.security.JwtUtil;
import com.pistasien.clothingstore.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/dashboard")
    public String profile(@RequestHeader("Authorization") String header){

        String token = header.substring(7);
        Claims claims = jwtUtil.validateToken(token);
        return claims.getSubject();
    }

    @GetMapping("/getAll")
    public Collection<User> alluser(){
        return userService.getallusers();
    }
}
