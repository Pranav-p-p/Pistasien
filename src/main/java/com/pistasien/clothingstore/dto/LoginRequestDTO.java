package com.pistasien.clothingstore.dto;

import jakarta.validation.constraints.Pattern;

public class LoginRequestDTO {

    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email"
    )
    private String email;
    private String password;

    public LoginRequestDTO(){}

    public void setEmail(){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
