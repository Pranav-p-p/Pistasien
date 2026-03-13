package com.pistasien.clothingstore.exception;

public class PasswordCannotBeBlankException extends RuntimeException {
    public PasswordCannotBeBlankException(String message) {
        super(message);
    }
}
