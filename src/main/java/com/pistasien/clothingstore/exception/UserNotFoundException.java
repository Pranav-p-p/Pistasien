package com.pistasien.clothingstore.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {

        super(message);
    }
}
