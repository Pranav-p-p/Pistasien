package com.pistasien.clothingstore.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public class OtpRequestDTO {

    @NotBlank
    @Pattern(regexp = "^(\\+91)?[6-9][0-9]{9}$",
            message = "Invalid phone number")
    private String phone;

    public OtpRequestDTO(){}

    public void setPhone(){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }
}
