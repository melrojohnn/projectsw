package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing UserModel data in the database.
 * This is the data access layer for application users.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    /**
     * Finds a user by their username.
     * This method is crucial for the UserDetailsService to load users for authentication.
     * @param username The username to search for.
     * @return an Optional containing the found user, or empty if not found.
     */
    Optional<UserModel> findByUsername(String username);
}
