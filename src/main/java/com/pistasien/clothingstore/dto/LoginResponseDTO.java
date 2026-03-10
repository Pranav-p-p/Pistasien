package com.pistasien.clothingstore.dto;

import com.pistasien.clothingstore.entity.User;
import lombok.Getter;
import lombok.Setter;

public class LoginResponseDTO {

    @Getter
    @Setter
    private String loginToken;

    @Getter
    @Setter
    private Long loginResponseId;

    @Getter
    @Setter
    private User.Role loginResponseRole;

    @Getter
    @Setter
    private String LoginResponseEmail;

}
