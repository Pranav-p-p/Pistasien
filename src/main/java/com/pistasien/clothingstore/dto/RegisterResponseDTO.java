package com.pistasien.clothingstore.dto;

import com.pistasien.clothingstore.entity.User;
import lombok.Getter;
import lombok.Setter;

public class RegisterResponseDTO {
    @Getter
    private Long responseId;
    @Getter
    private String responseEmail;
    @Setter
    @Getter
    private String responseUserName;
    private User.Role responseRole;

    public void setResponse_id(Long responseId){
        this.responseId = responseId;
    }

    public void setResponse_email(String responseEmail){
        this.responseEmail = responseEmail;
    }

    public void setResponse_role(){
        this.responseRole = User.Role.customer;
    }

    public User.Role getResponseRole(){
        return User.Role.customer;
    }
}
