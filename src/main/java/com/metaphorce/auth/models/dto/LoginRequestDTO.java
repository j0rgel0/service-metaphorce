package com.metaphorce.auth.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for login request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;
}
