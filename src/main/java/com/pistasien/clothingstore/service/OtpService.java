package com.pistasien.clothingstore.service;

import com.pistasien.clothingstore.dto.OtpRequestDTO;
import com.pistasien.clothingstore.dto.OtpRequestResponseDTO;
import com.pistasien.clothingstore.dto.VerifyOtpRequestDTO;
import com.pistasien.clothingstore.dto.VerifyOtpResponseDTO;
import com.pistasien.clothingstore.entity.Otp;
import com.pistasien.clothingstore.exception.AttemptLimitReachedException;
import com.pistasien.clothingstore.exception.OtpExpiredException;
import com.pistasien.clothingstore.repository.OtpRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;

@Service
public class OtpService {

    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);
    private final OtpRepository otpRepository;

    public OtpService(OtpRepository repository) {
        this.otpRepository = repository;
    }

    public String generateOtp(String phone){
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);

        logger.info("Generated OTP {} for phone {} ",otp,phone);
        return String.valueOf(otp);
    }

    public OtpRequestResponseDTO requestOtp(OtpRequestDTO request){

        OtpRequestResponseDTO response = new OtpRequestResponseDTO();

        Optional<Otp> exist = otpRepository.findByOtpPhone(request.getPhone());

        if(exist.isPresent()){
            Otp present = exist.get();
            if(present.getAttempt() == 0){
                otpRepository.delete(present);
                throw new AttemptLimitReachedException(request.getPhone());
            }
            present.setAttempt(present.getAttempt() - 1);
            present.setExpire(LocalDateTime.now().plusMinutes(5));
            present.setOtpCode(generateOtp(request.getPhone()));
            present.setCreatedAt(LocalDateTime.now());
            Otp save = otpRepository.save(present);
            response.setSuccess(true);

        }else {
            Otp otp = new Otp();
            otp.setAttempt(3);

            otp.setOtpPhone(request.getPhone());
            otp.setOtpCode(generateOtp(request.getPhone()));
            otp.setCreatedAt(LocalDateTime.now());
            otp.setExpire(LocalDateTime.now().plusMinutes(5));

            Otp save = otpRepository.save(otp);
            response.setSuccess(true);
        }
        return response;
    }

    public VerifyOtpResponseDTO verify(VerifyOtpRequestDTO request){

        VerifyOtpResponseDTO response = new VerifyOtpResponseDTO();

        Otp verifyOtp = otpRepository
                .findByOtpPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("OTP not found."));

        System.out.println("Expire: " + verifyOtp.getExpire());
        System.out.println("Current: " + LocalDateTime.now());
        if(!verifyOtp.getExpire().isAfter(LocalDateTime.now())){
            throw  new OtpExpiredException("OTP has expired.");
        }

        if(!verifyOtp.getOtpCode().equals(request.getOtp())){
            throw new RuntimeException("Invalid OTP.");
        }
        otpRepository.delete(verifyOtp);
        response.setResult(true);
        return response;
    }
}
