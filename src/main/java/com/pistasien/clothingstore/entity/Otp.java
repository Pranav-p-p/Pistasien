package com.pistasien.clothingstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verification")
public class Otp {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpId;

    @Setter
    @Column(name = "phone")
    private String otpPhone;

    @Getter
    @Setter
    @Column(name = "otp_code")
    private String otpCode;

    @Getter
    @Setter
    @Column(name = "expires_at")
    private LocalDateTime expire;

    @Setter
    @Column(name = "attempt_count")
    private Integer attempt;

    public int getAttempt(){
        return attempt;
    }

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
