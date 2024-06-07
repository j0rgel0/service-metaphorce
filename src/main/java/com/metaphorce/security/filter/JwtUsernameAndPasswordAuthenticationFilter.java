package com.metaphorce.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.metaphorce.auth.models.dto.JwtResponseDTO;
import com.metaphorce.auth.models.dto.LoginRequestDTO;
import com.metaphorce.auth.util.ApiConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

/**
 * Filter for handling username and password authentication and generating JWT tokens.
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String jwtSecret;

    /**
     * Constructs a JwtUsernameAndPasswordAuthenticationFilter with the given authentication manager and JWT secret.
     *
     * @param authenticationManager the authentication manager.
     * @param jwtSecret the secret key used for signing the JWT token.
     */
    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        setFilterProcessesUrl(ApiConstants.Auth.LOGIN_URL);
    }

    /**
     * Attempts to authenticate the user by extracting the login request data from the request.
     *
     * @param request the HttpServletRequest object.
     * @param response the HttpServletResponse object.
     * @return the Authentication object if authentication is successful.
     * @throws AuthenticationException if an authentication error occurs.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDTO loginRequestDTO = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles successful authentication by generating a JWT token and adding it to the response.
     *
     * @param request the HttpServletRequest object.
     * @param response the HttpServletResponse object.
     * @param chain the FilterChain object.
     * @param authResult the Authentication object resulting from the successful authentication.
     * @throws IOException if an input or output error occurs.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));

        response.addHeader("Authorization", "Bearer " + token);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(new JwtResponseDTO(token, "Bearer", role)));
        response.getWriter().flush();
    }
}
