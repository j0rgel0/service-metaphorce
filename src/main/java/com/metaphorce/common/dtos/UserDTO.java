package com.metaphorce.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for user information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    /**
     * The unique identifier of the user.
     */
    private String id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The date and time when the user was created.
     */
    private LocalDateTime creationDate;

    /**
     * The date and time when the user was last updated.
     */
    private LocalDateTime lastUpdate;

    /**
     * Indicates whether the user is soft deleted.
     */
    private boolean softDelete;

    /**
     * The role of the user.
     */
    private String role;
}
