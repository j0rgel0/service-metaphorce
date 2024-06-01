package com.metaphorce.security.service;


import com.metaphorce.user.entities.UserEntity;
import com.metaphorce.user.respositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service for loading user details from the database.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a UserDetailsServiceImpl with the given user repository.
     *
     * @param userRepository the user repository for accessing user data.
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details by username (email).
     *
     * @param email the email of the user.
     * @return the UserDetails object containing user information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        String role = getRole(userEntity.getRole());
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.singletonList(authority));
    }

    /**
     * Maps the role string to a proper role name.
     *
     * @param role the role string from the user entity.
     * @return the mapped role name.
     */
    private String getRole(String role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        return switch (role.toUpperCase()) {
            case "ADMIN" -> "ROLE_ADMINISTRATOR";
            case "USER" -> "ROLE_USER";
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}
