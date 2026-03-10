package com.pistasien.clothingstore.dto;

public class LoginRequestDTO {
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
