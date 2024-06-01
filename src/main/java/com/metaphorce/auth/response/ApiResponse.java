package com.metaphorce.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Standard API response wrapper.
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    /**
     * The timestamp when the response was created.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status of the response.
     */
    private HttpStatus status;

    /**
     * The message associated with the response.
     */
    private String message;

    /**
     * Additional details about the response.
     */
    private String details;

    /**
     * Constructs an ApiResponse with the given status and details.
     *
     * @param status  the HTTP status of the response.
     * @param details additional details about the response.
     */
    public ApiResponse(HttpStatus status, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.details = details;
    }

    /**
     * Constructs an ApiResponse with the given status, message, and details.
     *
     * @param status  the HTTP status of the response.
     * @param message the message associated with the response.
     * @param details additional details about the response.
     */
    public ApiResponse(HttpStatus status, String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
