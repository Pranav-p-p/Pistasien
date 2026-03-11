package com.pistasien.clothingstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class RegisterRequestDTO {
    @Getter
    private String phone;
    @Getter
    private String userName;
    @Getter
    private String password;
    @Getter
    @Setter
    private String userEmail;
    @Setter
    @Getter
    private boolean option;

    public RegisterRequestDTO(){}

    public void setUserName(){this.userName = userName; }

    public void setIdentifier(){
        this.phone = phone;
    }

    public void setPassword(){
        this.password = password;
    }

}
