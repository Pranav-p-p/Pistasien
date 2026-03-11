package com.pistasien.clothingstore.exception;

public class AttemptLimitReachedException extends RuntimeException {
    public AttemptLimitReachedException(String message) {
        super("Otp limit reached for : " + message);
    }
}
