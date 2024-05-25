package com.metaphorce.user.services.impl;

import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.common.exceptions.UserAlreadyExistsException;
import com.metaphorce.common.exceptions.UserNotFoundException;
import com.metaphorce.user.entities.User;
import com.metaphorce.user.mappers.UserMapper;
import com.metaphorce.user.respositories.UserRepository;
import com.metaphorce.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserService interface for handling user operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Constructs a new UserServiceImpl with the specified UserRepository and UserMapper.
     *
     * @param userRepository the repository for accessing user data
     * @param userMapper the mapper for converting between UserDTO and User entities
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Creates a new user.
     *
     * @param userDTO the UserDTO object representing the user to be created
     * @return the created UserDTO object
     * @throws UserAlreadyExistsException if a user with the given email already exists
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + userDTO.getEmail());
        }

        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of UserDTO objects
     */
    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDTO object
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public UserDTO getUserById(String id) {
        return userRepository.findById(id).map(userMapper::toDTO).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDTO the UserDTO object representing the updated user information
     * @return the updated UserDTO object
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    /**
     * Retrieves the names of all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user names
     */
    @Override
    public Page<String> getUsersName(Pageable pageable) {
        return userRepository.findAll(pageable).map(User::getName);
    }
}
