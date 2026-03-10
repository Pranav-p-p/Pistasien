package com.pistasien.clothingstore.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super("The email" + message + "already exists.");
    }
}
