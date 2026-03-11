package com.pistasien.clothingstore.dto;

import lombok.Getter;
import lombok.Setter;

public class VerifyOtpRequestDTO {

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String otp;

    VerifyOtpRequestDTO(){}

}
