package com.metaphorce.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Utility class for generating and handling JWT tokens.
 */
@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param authentication the authentication object containing user details.
     * @return a JWT token as a string.
     */
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        String role = principal.getAuthorities().iterator().next().getAuthority();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plus(Duration.ofMillis(jwtExpirationMs));

        Instant issuedAt = now.atZone(ZoneId.systemDefault()).toInstant();
        Instant expiresAt = expirationTime.atZone(ZoneId.systemDefault()).toInstant();

        return JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("role", role)
                .withIssuedAt(Date.from(issuedAt))
                .withExpiresAt(Date.from(expiresAt))
                .sign(Algorithm.HMAC512(jwtSecret));
    }
}
