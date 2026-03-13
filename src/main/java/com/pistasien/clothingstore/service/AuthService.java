package com.pistasien.clothingstore.service;

import com.pistasien.clothingstore.dto.*;
import com.pistasien.clothingstore.entity.User;
import com.pistasien.clothingstore.exception.*;
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

        if(request.getUserName().isBlank()){
            throw new UserNameCannotBeBlankException("Username cannot be empty.");
        }

        if (request.getPassword().isBlank()){
            throw new PasswordCannotBeBlankException("Password cannot be empty.");
        }

        User user = new User();
        System.out.println("Email received: " + request.getUserEmail());
        user.setUserPhone(request.getPhone());
        if (request.getUserEmail() != null && request.getUserEmail().isBlank()) {
            user.setUser_email(null);
        }
        else {
            user.setUser_email(request.getUserEmail());
                }

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

        response.setCreated(true);

        response.setResponse_id(saved.getUser_id());

        response.setResponse_email(saved.getUser_email());

        response.setResponseUserName(saved.getUserName());

        response.setResponsePhone(saved.getUserPhone());

        response.setCreatedAt(saved.getCreted_at());

        response.setResponse_role();

        return response;
    }

    public LoginResponseDTO loginService(LoginRequestDTO loginRequest){

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String phoneRegex = "^(\\+91)?[6-9][0-9]{9}$";
        User user;
        LoginResponseDTO response = null;

        if(loginRequest.getInput().matches(emailRegex)){
            Optional<User> existing = repository.findByUserEmail(loginRequest.getInput());

            if(!existing.isPresent()){
                throw new UserNotFoundException("Try logging in using Phone no");
            }

            user = existing.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String loginPassword = loginRequest.getPassword();
            String savedPassword = user.getUser_password();

            if(!passwordEncoder.matches(loginPassword,savedPassword)){
                throw new IncorrectPassword("Invalid Credentials.");
            }

            String token = jwtUtil.generateToken(user.getUser_id(),
                    user.getRole());

            UserDTO userResponse = new UserDTO(user.getUser_id(), user.getRole(),
                    user.getUser_email(), user.getUserPhone());

            response = new LoginResponseDTO(token, userResponse);

        } else if (loginRequest.getInput().matches(phoneRegex)) {
            Optional<User> existing = repository.findByUserPhone(loginRequest.getInput());

            if(!existing.isPresent()){
                throw new UserNotFoundException("Try logging in using Phone no");
            }

            user = existing.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String loginPassword = loginRequest.getPassword();
            String savedPassword = user.getUser_password();

            if(!passwordEncoder.matches(loginPassword,savedPassword)) {
                throw new IncorrectPassword("Invalid Credentials.");
            }

            String token = jwtUtil.generateToken(user.getUser_id(),
                    user.getRole());

            UserDTO userResponse = new UserDTO(user.getUser_id(), user.getRole(),
                    user.getUser_email(), user.getUserPhone());

            response = new LoginResponseDTO(token, userResponse);
            
        }else {
            throw new InvalidInputFormatException("Invalid Email/Phone no");
        }

        return response;
    }

}
