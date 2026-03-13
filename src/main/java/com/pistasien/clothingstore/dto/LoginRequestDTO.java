package com.pistasien.clothingstore.dto;

import lombok.Setter;

public class LoginRequestDTO {

    @Setter
    private String input;
    @Setter
    private String password;

    public LoginRequestDTO(){}

    public String getInput(){
        return input;
    }

    public String getPassword(){
        return password;
    }
}
