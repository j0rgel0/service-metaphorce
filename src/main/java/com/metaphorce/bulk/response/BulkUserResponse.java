package com.metaphorce.bulk.response;

import com.metaphorce.common.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Response class for handling the results of bulk user operations.
 */
@Data
@AllArgsConstructor
public class BulkUserResponse {

    /**
     * List of UserDTO objects representing successfully created users.
     */
    private List<UserDTO> successfulUsers;

    /**
     * List of UserError objects representing users that were not created successfully.
     */
    private List<UserError> unsuccessfulUsers;

    /**
     * Number of users successfully created.
     */
    private int numOfUsersCreated;

    /**
     * Number of users that were not created.
     */
    private int numOfUsersNotCreated;
}