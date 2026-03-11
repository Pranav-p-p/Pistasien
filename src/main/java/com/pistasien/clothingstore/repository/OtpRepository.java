package com.pistasien.clothingstore.repository;

import com.pistasien.clothingstore.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByOtpPhone(String otpPhone);
}
