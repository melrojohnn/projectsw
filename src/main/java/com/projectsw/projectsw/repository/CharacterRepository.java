package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {

    /**
     * Finds a character by its public-facing UUID.
     * @param publicId The UUID to search for.
     * @return an Optional containing the found character, or empty if not found.
     */
    Optional<CharacterModel> findByPublicId(UUID publicId);
}
