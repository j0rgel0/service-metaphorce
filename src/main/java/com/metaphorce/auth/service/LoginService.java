package com.metaphorce.auth.service;


import com.metaphorce.auth.models.dto.JwtResponseDTO;
import com.metaphorce.auth.models.dto.LoginRequestDTO;

/**
 * Service interface for handling login operations.
 */
public interface LoginService {

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param loginRequestDTO the login request data transfer object containing email and password.
     * @return a JwtResponseDTO containing the JWT token, token type, and user role.
     */
    JwtResponseDTO login(LoginRequestDTO loginRequestDTO);
}
