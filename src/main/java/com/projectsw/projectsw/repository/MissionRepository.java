package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.MissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionRepository extends JpaRepository<MissionModel, Long> {

    /**
     * Finds a mission by its public-facing UUID.
     * @param publicId The UUID to search for.
     * @return an Optional containing the found mission, or empty if not found.
     */
    Optional<MissionModel> findByPublicId(UUID publicId);

    /**
     * Finds all missions and eagerly fetches their associated members in a single query.
     * This solves the N+1 query problem when accessing the members list.
     * @return A list of all missions with their member data pre-loaded.
     */
    @Query("SELECT m FROM MissionModel m LEFT JOIN FETCH m.members")
    List<MissionModel> findAllWithMembers();
}
