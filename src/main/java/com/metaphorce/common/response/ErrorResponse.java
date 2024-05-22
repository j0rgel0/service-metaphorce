package com.metaphorce.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing an error response with a message and status code.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    /**
     * The error message describing the issue.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     */
    private int status;
}