package com.metaphorce.auth.controller;

import com.metaphorce.auth.models.dto.JwtResponseDTO;
import com.metaphorce.auth.models.dto.LoginRequestDTO;
import com.metaphorce.auth.service.LoginService;
import com.metaphorce.auth.util.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication requests.
 */
@RestController
@RequestMapping(ApiConstants.AUTH_BASE_URL)
public class LoginController {

    private final LoginService loginService;

    /**
     * Constructor for LoginController.
     *
     * @param loginService The service used for login operations.
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequestDTO The login request data transfer object containing username and password.
     * @return A ResponseEntity containing the JWT response data transfer object.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        JwtResponseDTO jwtResponseDTO = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}
