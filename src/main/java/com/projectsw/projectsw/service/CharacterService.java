package com.projectsw.projectsw.service;

import com.projectsw.projectsw.dto.CharacterCreateDTO;
import com.projectsw.projectsw.dto.CharacterResponseDTO;
import com.projectsw.projectsw.dto.MissionSummaryDTO;
import com.projectsw.projectsw.enums.*;
import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.repository.CharacterRepository;
import com.projectsw.projectsw.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MissionRepository missionRepository; // Dependency for finding missions

    // Updated constructor to inject both repositories
    public CharacterService(CharacterRepository characterRepository, MissionRepository missionRepository) {
        this.characterRepository = characterRepository;
        this.missionRepository = missionRepository;
    }

    public List<CharacterResponseDTO> getAllCharacters() {
        return characterRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CharacterResponseDTO createCharacter(CharacterCreateDTO characterDTO) {
        validateRankForFaction(characterDTO.getFaction(), characterDTO.getRank());

        CharacterModel character = new CharacterModel();
        character.setName(characterDTO.getName());
        character.setEmail(characterDTO.getEmail());
        character.setAge(characterDTO.getAge());
        character.setFaction(characterDTO.getFaction());
        character.setRank(characterDTO.getRank().toUpperCase());
        character.setHomeland(characterDTO.getHomeland());

        // ** New Logic: Assign Mission if ID is provided **
        if (characterDTO.getMissionId() != null) {
            MissionModel mission = missionRepository.findByPublicId(characterDTO.getMissionId())
                    .orElseThrow(() -> new EntityNotFoundException("Mission not found with ID: " + characterDTO.getMissionId()));
            character.setMission(mission);
        }

        CharacterModel savedCharacter = characterRepository.save(character);
        return toResponseDTO(savedCharacter);
    }

    public CharacterResponseDTO updateCharacter(UUID publicId, CharacterCreateDTO characterDTO) {
        validateRankForFaction(characterDTO.getFaction(), characterDTO.getRank());

        CharacterModel existingCharacter = characterRepository.findByPublicId(publicId)
                .orElseThrow(() -> new EntityNotFoundException("Character not found with ID: " + publicId));

        existingCharacter.setName(characterDTO.getName());
        existingCharacter.setEmail(characterDTO.getEmail());
        existingCharacter.setAge(characterDTO.getAge());
        existingCharacter.setFaction(characterDTO.getFaction());
        existingCharacter.setRank(characterDTO.getRank().toUpperCase());
        existingCharacter.setHomeland(characterDTO.getHomeland());

        // ** New Logic: Update Mission if ID is provided **
        if (characterDTO.getMissionId() != null) {
            MissionModel mission = missionRepository.findByPublicId(characterDTO.getMissionId())
                    .orElseThrow(() -> new EntityNotFoundException("Mission not found with ID: " + characterDTO.getMissionId()));
            existingCharacter.setMission(mission);
        } else {
            existingCharacter.setMission(null); // Allow un-assigning a mission
        }

        CharacterModel updatedCharacter = characterRepository.save(existingCharacter);
        return toResponseDTO(updatedCharacter);
    }

    public boolean deleteCharacter(UUID publicId) {
        return characterRepository.findByPublicId(publicId).map(character -> {
            characterRepository.delete(character);
            return true;
        }).orElse(false);
    }

    public CharacterResponseDTO getCharacterByPublicId(UUID publicId) {
        return characterRepository.findByPublicId(publicId)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    private CharacterResponseDTO toResponseDTO(CharacterModel character) {
        CharacterResponseDTO dto = new CharacterResponseDTO();
        dto.setId(character.getPublicId());
        dto.setName(character.getName());
        dto.setEmail(character.getEmail());
        dto.setAge(character.getAge());
        dto.setFaction(character.getFaction());
        dto.setRank(character.getRank());
        dto.setHomeland(character.getHomeland());

        if (character.getMission() != null) {
            MissionSummaryDTO missionDTO = new MissionSummaryDTO(
                character.getMission().getPublicId(),
                character.getMission().getTitle(),
                character.getMission().getDescription(),
                character.getMission().getStatus()
            );
            dto.setMission(missionDTO);
        }
        return dto;
    }

    private void validateRankForFaction(Faction faction, String rank) {
        if (faction == null || rank == null || rank.trim().isEmpty()) {
            throw new IllegalArgumentException("Faction and Rank cannot be null or empty.");
        }

        try {
            String upperRank = rank.toUpperCase();
            switch (faction) {
                case REBEL_ALLIANCE:
                    RebelRank.valueOf(upperRank);
                    break;
                case GALACTIC_EMPIRE:
                    EmpireRank.valueOf(upperRank);
                    break;
                case JEDI_ORDER:
                    LightsideRank.valueOf(upperRank);
                    break;
                case SITH:
                    DarksideRank.valueOf(upperRank);
                    break;
                case DROID_ARMY:
                    DroidRank.valueOf(upperRank);
                    break;
                case UNAFFILIATED:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown faction provided: " + faction);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rank '" + rank + "' is not valid for faction '" + faction + "'.");
        }
    }
}
