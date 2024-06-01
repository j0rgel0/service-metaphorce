package com.metaphorce.bulk.controller;

import com.metaphorce.bulk.response.BulkUserResponse;
import com.metaphorce.bulk.services.UserBulkService;
import com.metaphorce.common.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling bulk user operations.
 */
@RestController
@RequestMapping("/api/v1/bulk-users")
public class UserBulkController {

    private final UserBulkService bulkUserService;

    /**
     * Constructs a new UserBulkController with the specified UserBulkService.
     *
     * @param bulkUserService the service for handling bulk user operations
     */
    @Autowired
    public UserBulkController(UserBulkService bulkUserService) {
        this.bulkUserService = bulkUserService;
    }

    /**
     * Creates multiple users in bulk.
     *
     * @param userDTOs a list of UserDTO objects representing the users to be created
     * @return a ResponseEntity containing the BulkUserResponse
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<BulkUserResponse> createUsersByBulk(@RequestBody List<UserDTO> userDTOs) {
        BulkUserResponse response = bulkUserService.createUsersByBulk(userDTOs);
        return ResponseEntity.ok(response);
    }
}

