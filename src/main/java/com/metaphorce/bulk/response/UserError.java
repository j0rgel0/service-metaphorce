package com.metaphorce.bulk.response;

import com.metaphorce.common.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing an error encountered during the creation of a user.
 */
@Data
@AllArgsConstructor
public class UserError {

    /**
     * The UserDTO object representing the user that encountered an error.
     */
    private UserDTO user;

    /**
     * The error message describing the issue encountered.
     */
    private String errorMessage;
}