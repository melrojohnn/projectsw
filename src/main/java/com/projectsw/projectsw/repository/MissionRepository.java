package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.MissionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MissionRepository extends JpaRepository<MissionModel, Long> {

    /**
     * Finds a mission by its public-facing UUID.
     * @param publicId The UUID to search for.
     * @return an Optional containing the found mission, or empty if not found.
     */
    Optional<MissionModel> findByPublicId(UUID publicId);

}
