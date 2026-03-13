package com.pistasien.clothingstore.dto;

public class LoginRequestDTO {

    private String input;
    private String password;

    public LoginRequestDTO(){}

    public void setEmail(){
        this.input = input;
    }

    public String getInput(){
        return input;
    }

    public void setPassword(){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
