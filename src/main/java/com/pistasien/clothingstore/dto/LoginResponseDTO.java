package com.pistasien.clothingstore.dto;

import lombok.Getter;
import lombok.Setter;

public class LoginResponseDTO {

    @Getter
    private String loginToken;
    @Getter
    private UserDTO user;

    public LoginResponseDTO(String token, UserDTO user){
        this.loginToken = token;
        this.user = user;
    }

}
