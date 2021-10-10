package com.management.food.advice.exception;

public class InsufficientPointException extends RuntimeException {
    public InsufficientPointException() {
    }

    public InsufficientPointException(String message) {
        super(message);
    }

    public InsufficientPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
