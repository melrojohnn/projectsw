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

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    /**
     * Creates a new mission and sets its status to PENDING by default.
     * @param missionDTO The DTO with the data for the new mission.
     * @return The DTO of the newly created mission.
     */
    public MissionResponseDTO createMission(MissionCreateDTO missionDTO) {
        // Map DTO to entity
        MissionModel mission = new MissionModel();
        mission.setTitle(missionDTO.getTitle());
        mission.setDescription(missionDTO.getDescription());
        mission.setRank(missionDTO.getRank()); // This is the difficulty

        // ** Apply business rule: New missions always start as PENDING **
        mission.setStatus(MissionStatus.PENDING);

        MissionModel savedMission = missionRepository.save(mission);
        return toResponseDTO(savedMission);
    }

    /**
     * Retrieves a list of all missions and maps them to DTOs.
     * @return A list of MissionResponseDTO objects.
     */
    public List<MissionResponseDTO> getAllMissions() {
        return missionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a single mission by its public ID and maps it to a DTO.
     * @param publicId The public UUID of the mission.
     * @return The MissionResponseDTO object, or null if not found.
     */
    public MissionResponseDTO getMissionByPublicId(UUID publicId) {
        return missionRepository.findByPublicId(publicId)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    /**
     * Updates an existing mission from a DTO.
     * @param publicId The public UUID of the mission to update.
     * @param missionDTO The DTO with the new data.
     * @return The DTO of the updated mission, or null if not found.
     */
    public MissionResponseDTO updateMission(UUID publicId, MissionCreateDTO missionDTO) {
        MissionModel existingMission = missionRepository.findByPublicId(publicId).orElse(null);

        if (existingMission != null) {
            existingMission.setTitle(missionDTO.getTitle());
            existingMission.setDescription(missionDTO.getDescription());
            existingMission.setRank(missionDTO.getRank());
            // Note: We might want a separate method/DTO to update the status.

            MissionModel updatedMission = missionRepository.save(existingMission);
            return toResponseDTO(updatedMission);
        } else {
            return null;
        }
    }

    /**
     * Deletes a mission by its public ID.
     * @param publicId The public UUID of the mission to delete.
     * @return true if deleted, false if not found.
     */
    public boolean deleteMission(UUID publicId) {
        return missionRepository.findByPublicId(publicId).map(mission -> {
            missionRepository.delete(mission);
            return true;
        }).orElse(false);
    }

    /**
     * Private helper method to map a MissionModel entity to a MissionResponseDTO.
     * @param mission The entity to map.
     * @return The resulting DTO.
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
