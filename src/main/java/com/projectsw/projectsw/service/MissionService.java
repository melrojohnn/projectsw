package com.projectsw.projectsw.service;

import com.projectsw.projectsw.dto.CharacterSummaryDTO;
import com.projectsw.projectsw.dto.MissionCreateDTO;
import com.projectsw.projectsw.dto.MissionResponseDTO;
import com.projectsw.projectsw.enums.MissionStatus;
import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class containing the business logic for mission management.
 */
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    /**
     * Constructs the MissionService with the necessary repository dependency.
     * @param missionRepository The repository for mission data access.
     */
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    /**
     * Creates a new mission based on the provided DTO.
     * New missions are always created with a PENDING status.
     * @param missionDTO The DTO containing the new mission's data.
     * @return The DTO of the newly created mission.
     */
    public MissionResponseDTO createMission(MissionCreateDTO missionDTO) {
        MissionModel mission = new MissionModel();
        mission.setTitle(missionDTO.getTitle());
        mission.setDescription(missionDTO.getDescription());
        mission.setRank(missionDTO.getRank());
        mission.setStatus(MissionStatus.PENDING);
        MissionModel savedMission = missionRepository.save(mission);
        return toResponseDTO(savedMission);
    }

    /**
     * Retrieves all missions from the database.
     * Uses an optimized query to fetch associated members eagerly to prevent N+1 issues.
     * @return A list of MissionResponseDTOs.
     */
    public List<MissionResponseDTO> getAllMissions() {
        return missionRepository.findAllWithMembers()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single mission by its public UUID.
     * @param publicId The public UUID of the mission.
     * @return An Optional containing the MissionResponseDTO if found, otherwise empty.
     */
    public MissionResponseDTO getMissionByPublicId(UUID publicId) {
        return missionRepository.findByPublicId(publicId)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    /**
     * Updates an existing mission with new data.
     * @param publicId The public UUID of the mission to update.
     * @param missionDTO The DTO containing the updated data.
     * @return The DTO of the updated mission, or null if the mission was not found.
     */
    public MissionResponseDTO updateMission(UUID publicId, MissionCreateDTO missionDTO) {
        MissionModel existingMission = missionRepository.findByPublicId(publicId).orElse(null);
        if (existingMission != null) {
            existingMission.setTitle(missionDTO.getTitle());
            existingMission.setDescription(missionDTO.getDescription());
            existingMission.setRank(missionDTO.getRank());
            MissionModel updatedMission = missionRepository.save(existingMission);
            return toResponseDTO(updatedMission);
        } else {
            return null;
        }
    }

    /**
     * Deletes a mission from the database.
     * @param publicId The public UUID of the mission to delete.
     * @return true if the mission was found and deleted, false otherwise.
     */
    public boolean deleteMission(UUID publicId) {
        return missionRepository.findByPublicId(publicId).map(mission -> {
            missionRepository.delete(mission);
            return true;
        }).orElse(false);
    }

    /**
     * Private helper method to convert a MissionModel entity to a MissionResponseDTO.
     * This also maps the list of assigned characters to a summary DTO.
     * @param mission The MissionModel entity.
     * @return The mapped MissionResponseDTO.
     */
    private MissionResponseDTO toResponseDTO(MissionModel mission) {
        MissionResponseDTO dto = new MissionResponseDTO();
        dto.setId(mission.getPublicId());
        dto.setTitle(mission.getTitle());
        dto.setDescription(mission.getDescription());
        dto.setStatus(mission.getStatus());
        dto.setRank(mission.getRank());
        if (mission.getMembers() != null) {
            dto.setMembers(mission.getMembers().stream().map(character ->
                    new CharacterSummaryDTO(
                            character.getPublicId(),
                            character.getName(),
                            character.getRank()
                    )
            ).collect(Collectors.toList()));
        }
        return dto;
    }
}
