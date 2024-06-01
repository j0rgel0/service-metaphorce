package com.metaphorce.user.mappers;

import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.user.entities.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between User entities and UserDTO objects.
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param userEntity the User entity to convert
     * @return the converted UserDTO object
     */
    public UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO object to convert
     * @return the converted User entity
     */
    public UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }
}
