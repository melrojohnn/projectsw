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

    public MissionResponseDTO createMission(MissionCreateDTO missionDTO) {
        MissionModel mission = new MissionModel();
        mission.setTitle(missionDTO.getTitle());
        mission.setDescription(missionDTO.getDescription());
        mission.setRank(missionDTO.getRank()); // **CORRIGIDO:** Usa o método setRank
        mission.setStatus(MissionStatus.PENDING);
        MissionModel savedMission = missionRepository.save(mission);
        return toResponseDTO(savedMission);
    }

    public List<MissionResponseDTO> getAllMissions() {
        return missionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MissionResponseDTO getMissionByPublicId(UUID publicId) {
        return missionRepository.findByPublicId(publicId)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    public MissionResponseDTO updateMission(UUID publicId, MissionCreateDTO missionDTO) {
        MissionModel existingMission = missionRepository.findByPublicId(publicId).orElse(null);
        if (existingMission != null) {
            existingMission.setTitle(missionDTO.getTitle());
            existingMission.setDescription(missionDTO.getDescription());
            existingMission.setRank(missionDTO.getRank()); // **CORRIGIDO:** Usa o método setRank
            MissionModel updatedMission = missionRepository.save(existingMission);
            return toResponseDTO(updatedMission);
        } else {
            return null;
        }
    }

    public boolean deleteMission(UUID publicId) {
        return missionRepository.findByPublicId(publicId).map(mission -> {
            missionRepository.delete(mission);
            return true;
        }).orElse(false);
    }

    private MissionResponseDTO toResponseDTO(MissionModel mission) {
        MissionResponseDTO dto = new MissionResponseDTO();
        dto.setId(mission.getPublicId());
        dto.setTitle(mission.getTitle());
        dto.setDescription(mission.getDescription());
        dto.setStatus(mission.getStatus());
        dto.setRank(mission.getRank()); // **CORRIGIDO:** Usa o método getRank
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