package com.pistasien.clothingstore.service;

import com.pistasien.clothingstore.dto.LoginRequestDTO;
import com.pistasien.clothingstore.dto.LoginResponseDTO;
import com.pistasien.clothingstore.dto.RegisterRequestDTO;
import com.pistasien.clothingstore.dto.RegisterResponseDTO;
import com.pistasien.clothingstore.entity.User;
import com.pistasien.clothingstore.exception.IncorrectPassword;
import com.pistasien.clothingstore.exception.UserFoundException;
import com.pistasien.clothingstore.exception.UserNotFoundException;
import com.pistasien.clothingstore.repository.UserRepository;
import com.pistasien.clothingstore.security.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repository, JwtUtil jwtUtil) {

        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public RegisterResponseDTO createUser(RegisterRequestDTO request){
        Optional<User> existing = repository.findByUserPhone(request.getPhone());
        if(existing.isPresent()){
            throw new UserFoundException(request.getPhone());
        }
        User user = new User();

        user.setUserPhone(request.getPhone());
        user.setUser_email(request.getUserEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUser_password(encoder.encode(request.getPassword()));

        user.setUserName(request.getUserName());

        user.setRole(User.Role.customer);

        user.setCreated_at(LocalDateTime.now());

        if(request.isOption()){
            user.setOption(true);
            user.setOptInAt(LocalDateTime.now());
        }

        User saved = repository.save(user);

        RegisterResponseDTO response = new RegisterResponseDTO();

        response.setResponse_id(saved.getUser_id());

        response.setResponse_email(saved.getUser_email());

        response.setResponseUserName(saved.getUserName());

        response.setResponsePhone(saved.getUserPhone());

        response.setCreatedAt(saved.getCreted_at());

        response.setResponse_role();

        return response;
    }

    public LoginResponseDTO loginService(LoginRequestDTO loginRequest){

        Optional<User> existing = repository.findByUserPhone(loginRequest.getInput());
        Optional<User> existingEmail = repository.findByUserEmail(loginRequest.getInput());

        if(!existing.isPresent() && !existingEmail.isPresent()){
            throw new UserNotFoundException("Invalid Credentials.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String phoneRegex = "^(\\+91)?[6-9][0-9]{9}$";
        User user;

        if(existing.isPresent()) {
            user = existing.get();
        }else {
            user = existingEmail.get();
        }

        LoginResponseDTO responseDTO = new LoginResponseDTO();

        if(loginRequest.getInput().matches(emailRegex)){
            repository.findByUserEmail(loginRequest.getInput())
                    .orElseThrow(() -> new RuntimeException(
                            "No account found. Try logging with phone number"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String loginPassword = loginRequest.getPassword();
            String savedPassword = user.getUser_password();

            if(!passwordEncoder.matches(loginPassword,savedPassword)){
                throw new IncorrectPassword("Invalid Credentials.");
            }

            responseDTO.setLoginToken(jwtUtil.generateToken(
                    user.getUser_id(),user.getUser_email(),user.getRole()));

            responseDTO.setLoginResponseEmail(user.getUser_email());
            responseDTO.setLoginResponseId(user.getUser_id());
            responseDTO.setLoginResponseRole(user.getRole());
            responseDTO.setLoginResponsePhone(user.getUserPhone());

        } else if (loginRequest.getInput().matches(phoneRegex)) {
            repository.findByUserPhone(loginRequest.getInput())
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String loginPassword = loginRequest.getPassword();
            String savedPassword = user.getUser_password();

            if(!passwordEncoder.matches(loginPassword,savedPassword)) {
                throw new IncorrectPassword("Invalid Credentials.");
            }

            responseDTO.setLoginToken(jwtUtil.generateToken(
                    user.getUser_id(),user.getUser_email(),user.getRole()));

            responseDTO.setLoginResponsePhone(user.getUserPhone());
            responseDTO.setLoginResponseId(user.getUser_id());
            responseDTO.setLoginResponseRole(user.getRole());
            responseDTO.setLoginResponseEmail(user.getUser_email());
            
        }

        return responseDTO;
    }

}
