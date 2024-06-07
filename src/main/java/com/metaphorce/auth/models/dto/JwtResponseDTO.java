package com.metaphorce.auth.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for JWT response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {

    /**
     * The JWT token.
     */
    private String token;

    /**
     * The type of the token, typically "Bearer".
     */
    private String type;

    /**
     * The role of the user.
     */
    private String role;
}
