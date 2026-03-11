package com.pistasien.clothingstore.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super("The Phone number " + message + "already exists.");
    }
}
