package com.metaphorce.user.services;

import com.metaphorce.common.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for handling user operations.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param userDTO the UserDTO object representing the user to be created
     * @return the created UserDTO object
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of UserDTO objects
     */
    Page<UserDTO> getAllUsers(Pageable pageable);

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDTO object
     */
    UserDTO getUserById(String id);

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDTO the UserDTO object representing the updated user information
     * @return the updated UserDTO object
     */
    UserDTO updateUser(String id, UserDTO userDTO);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(String id);

    /**
     * Retrieves the names of all users with pagination.
     *
     * @param pageable the pagination information
     * @return a page of user names
     */
    Page<String> getUsersName(Pageable pageable);
}
