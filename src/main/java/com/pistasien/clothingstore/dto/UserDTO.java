package com.pistasien.clothingstore.dto;

import com.pistasien.clothingstore.entity.User;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    @Getter
    private Long loginResponseId;

    @Getter
    private User.Role loginResponseRole;

    @Getter
    private String loginResponseEmail;

    @Getter
    private String loginResponsePhone;

    public UserDTO(Long loginResponseId, User.Role loginResponseRole,
                   String loginResponseEmail, String loginResponsePhone){

        this.loginResponseEmail = loginResponseEmail;
        this.loginResponseId = loginResponseId;
        this.loginResponsePhone = loginResponsePhone;
        this.loginResponseRole = loginResponseRole;
    }
}
