package com.pistasien.clothingstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password",nullable = false)
    private String userPassword;

    @Getter
    @Setter
    @Column(name = "user_name",nullable = false)
    private String userName;

    public enum Role{
        customer,
        admin
    }

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role",nullable = false)
    private Role role;

    @Setter
    @Column(name = "created_at",nullable = false)
    private LocalDateTime created_at;

    @Getter
    @Setter
    @Column(name = "user_phone",nullable = false,unique = true)
    private String userPhone;

    @Getter
    @Setter
    @Column(name = "opt_in")
    private boolean option;

    @Setter
    @Column(name = "opt_in_at")
    private LocalDateTime optInAt;

    public User(){}

    public User(String email, String password){
        this.userEmail = email;
        this.userPassword = password;
    }

    public Long getUser_id(){
        return userId;
    }

    public void setUser_email(String email) {
        this.userEmail = email;
    }

    public String getUser_email(){
        return userEmail;
    }

    public void setUser_password(String password){
        this.userPassword = password;
    }

    public String getUser_password(){
        return userPassword;
    }

    public LocalDateTime getCreted_at(){
        return created_at;
    }

}
