package com.pistasien.clothingstore.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public class OtpRequestDTO {

    @NotBlank
    private String phone;

    public OtpRequestDTO(){}

    public void setPhone(){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }
}
