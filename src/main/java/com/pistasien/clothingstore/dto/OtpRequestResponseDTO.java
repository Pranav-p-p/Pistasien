package com.pistasien.clothingstore.dto;

import lombok.Setter;

public class OtpRequestResponseDTO {

    @Setter
    private boolean success;

    public boolean isSuccess(){
        return success;
    }
}
