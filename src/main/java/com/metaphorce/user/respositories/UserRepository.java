package com.metaphorce.user.respositories;

import com.metaphorce.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

    /**
     * Checks if a user exists with the specified email.
     *
     * @param email the email to check for existence
     * @return true if a user exists with the specified email, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Deletes a user by their email.
     *
     * @param email the email of the user to be deleted
     */
    void deleteByEmail(String email);

    UserEntity findByEmail(String email);


}
