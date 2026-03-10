package com.pistasien.clothingstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class RegisterRequestDTO {
    @Getter
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email must contain a valid domain (example: user@gmail.com)"
    )
    private String email;
    @Getter
    private String userName;
    @Getter
    private String password;

    public RegisterRequestDTO(){}

    public void setUserName(){this.userName = userName; }

    public void setEmail(){
        this.email = email;
    }

    public void setPassword(){
        this.password = password;
    }

}
