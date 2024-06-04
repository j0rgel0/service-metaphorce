package com.metaphorce.common.exceptions;

/**
 * Exception thrown when invalid credentials are provided during authentication.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructs a new InvalidCredentialsException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
