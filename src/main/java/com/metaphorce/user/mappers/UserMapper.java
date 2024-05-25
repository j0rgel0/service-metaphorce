package com.metaphorce.user.mappers;

import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.user.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User entities and UserDTO objects.
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the converted UserDTO object
     */
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO object to convert
     * @return the converted User entity
     */
    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }
}
