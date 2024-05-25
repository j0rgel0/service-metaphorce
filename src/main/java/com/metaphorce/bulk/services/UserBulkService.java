package com.metaphorce.bulk.services;

import com.metaphorce.bulk.processor.UserBulkProcessor;
import com.metaphorce.bulk.response.BulkUserResponse;
import com.metaphorce.bulk.response.UserError;
import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.common.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling bulk user creation operations.
 */
@Service
public class UserBulkService {

    private final UserBulkProcessor userBulkProcessor;

    /**
     * Constructs a new UserBulkService with the specified UserBulkProcessor.
     *
     * @param userBulkProcessor the processor for handling individual user creation
     */
    @Autowired
    public UserBulkService(UserBulkProcessor userBulkProcessor) {
        this.userBulkProcessor = userBulkProcessor;
    }

    /**
     * Creates multiple users in bulk.
     *
     * @param userDTOs a list of UserDTO objects representing the users to be created
     * @return a BulkUserResponse containing the results of the bulk user creation operation
     */
    public BulkUserResponse createUsersByBulk(List<UserDTO> userDTOs) {
        List<UserDTO> successfulUsers = new ArrayList<>();
        List<UserError> unsuccessfulUsers = new ArrayList<>();

        for (UserDTO userDTO : userDTOs) {
            try {
                userBulkProcessor.processSingleUser(userDTO);
                successfulUsers.add(userDTO);
            } catch (UserAlreadyExistsException e) {
                unsuccessfulUsers.add(new UserError(userDTO, e.getMessage()));
            } catch (Exception e) {
                unsuccessfulUsers.add(new UserError(userDTO, "An unexpected error occurred: " + e.getMessage()));
            }
        }

        return new BulkUserResponse(successfulUsers, unsuccessfulUsers, successfulUsers.size(), unsuccessfulUsers.size());
    }
}
