package com.pistasien.clothingstore.exception;

public class UserNameCannotBeBlankException extends RuntimeException {
    public UserNameCannotBeBlankException(String message) {
        super(message);
    }
}
