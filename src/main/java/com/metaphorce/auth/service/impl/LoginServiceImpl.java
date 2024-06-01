package com.metaphorce.auth.service.impl;

import com.metaphorce.auth.models.dto.JwtResponseDTO;
import com.metaphorce.auth.models.dto.LoginRequestDTO;
import com.metaphorce.auth.service.LoginService;
import com.metaphorce.security.utils.JwtUtils;
import com.metaphorce.user.entities.UserEntity;
import com.metaphorce.user.respositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the LoginService interface.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    /**
     * Constructs a LoginServiceImpl with the necessary dependencies.
     *
     * @param authenticationManager the authentication manager for handling authentication.
     * @param jwtUtils the utility class for generating JWT tokens.
     * @param userRepository the repository for accessing user data.
     */
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param loginRequestDTO the login request data transfer object containing email and password.
     * @return a JwtResponseDTO containing the JWT token, token type, and user role.
     */
    @Override
    public JwtResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserEntity userEntity = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }

        String role = userEntity.getRole();

        return new JwtResponseDTO(jwt, "Bearer", role);
    }
}
