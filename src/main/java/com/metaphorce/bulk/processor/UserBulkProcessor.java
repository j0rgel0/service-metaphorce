package com.metaphorce.bulk.processor;

import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.common.exceptions.UserAlreadyExistsException;
import com.metaphorce.user.entities.UserEntity;
import com.metaphorce.user.mappers.UserMapper;
import com.metaphorce.user.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processor for handling the creation of users in bulk.
 */
@Component
public class UserBulkProcessor {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Constructs a new UserBulkProcessor with the specified UserRepository and UserMapper.
     *
     * @param userRepository the repository for accessing user data
     * @param userMapper the mapper for converting between UserDTO and User entities
     */
    @Autowired
    public UserBulkProcessor(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Processes a single user by creating a new user entity in the repository.
     * Throws UserAlreadyExistsException if a user with the same email already exists.
     *
     * @param userDTO the UserDTO object representing the user to be created
     * @throws UserAlreadyExistsException if a user with the given email already exists
     */
    public void processSingleUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + userDTO.getEmail());
        }

        UserEntity userEntity = userMapper.toEntity(userDTO);
        userRepository.save(userEntity);
    }
}
