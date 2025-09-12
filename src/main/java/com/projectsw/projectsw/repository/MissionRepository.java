package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.MissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing MissionModel data in the database.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface MissionRepository extends JpaRepository<MissionModel, Long> {

    /**
     * Finds a mission by its public-facing UUID.
     * @param publicId The UUID to search for.
     * @return an Optional containing the found mission, or empty if not found.
     */
    Optional<MissionModel> findByPublicId(UUID publicId);

    /**
     * Finds all missions and eagerly fetches their associated members in a single query.
     * This custom query is designed to solve the N+1 problem when accessing the mission members list.
     * @return A list of all missions with their member data pre-loaded.
     */
    @Query("SELECT m FROM MissionModel m LEFT JOIN FETCH m.members")
    List<MissionModel> findAllWithMembers();
}
