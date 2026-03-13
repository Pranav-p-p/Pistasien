package com.pistasien.clothingstore.dto;

import com.pistasien.clothingstore.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class RegisterResponseDTO {
    private Long responseId;
    private String responseEmail;
    @Setter
    private String responseUserName;
    private User.Role responseRole;
    @Setter
    private String responsePhone;

    @Setter
    @Getter
    private boolean created;

    @Setter
    private LocalDateTime createdAt;
    public void setResponse_id(Long responseId){
        this.responseId = responseId;
    }

    public void setResponse_email(String responseEmail){
        this.responseEmail = responseEmail;
    }

    public void setResponse_role(){
        this.responseRole = User.Role.customer;
    }

}
