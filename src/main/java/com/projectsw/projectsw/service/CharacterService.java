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

/**
 * Service class containing the business logic for character management.
 * It acts as an intermediary between the controller and the repository layers.
 */
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MissionRepository missionRepository;

    /**
     * Constructs the CharacterService with necessary repository dependencies.
     * @param characterRepository The repository for character data access.
     * @param missionRepository The repository for mission data access.
     */
    public CharacterService(CharacterRepository characterRepository, MissionRepository missionRepository) {
        this.characterRepository = characterRepository;
        this.missionRepository = missionRepository;
    }

    /**
     * Retrieves all characters from the database.
     * Uses an optimized query to fetch associated missions eagerly.
     * @return A list of CharacterResponseDTOs.
     */
    public List<CharacterResponseDTO> getAllCharacters() {
        return characterRepository.findAllWithMission()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new character based on the provided DTO.
     * @param characterDTO The DTO containing the new character's data.
     * @return The DTO of the newly created character.
     */
    public CharacterResponseDTO createCharacter(CharacterCreateDTO characterDTO) {
        validateRankForFaction(characterDTO.getFaction(), characterDTO.getRank());

        CharacterModel character = new CharacterModel();
        character.setName(characterDTO.getName());
        character.setEmail(characterDTO.getEmail());
        character.setAge(characterDTO.getAge());
        character.setFaction(characterDTO.getFaction());
        character.setRank(characterDTO.getRank().toUpperCase());
        character.setHomeland(characterDTO.getHomeland());
        character.setImageUrl(characterDTO.getImageUrl());

        if (characterDTO.getMissionId() != null) {
            MissionModel mission = missionRepository.findByPublicId(characterDTO.getMissionId())
                    .orElseThrow(() -> new EntityNotFoundException("Mission not found with ID: " + characterDTO.getMissionId()));
            character.setMission(mission);
        }

        CharacterModel savedCharacter = characterRepository.save(character);
        return toResponseDTO(savedCharacter);
    }

    /**
     * Updates an existing character with new data.
     * @param publicId The public UUID of the character to update.
     * @param characterDTO The DTO containing the updated data.
     * @return The DTO of the updated character.
     */
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
        existingCharacter.setImageUrl(characterDTO.getImageUrl());

        if (characterDTO.getMissionId() != null) {
            MissionModel mission = missionRepository.findByPublicId(characterDTO.getMissionId())
                    .orElseThrow(() -> new EntityNotFoundException("Mission not found with ID: " + characterDTO.getMissionId()));
            existingCharacter.setMission(mission);
        } else {
            existingCharacter.setMission(null);
        }

        CharacterModel updatedCharacter = characterRepository.save(existingCharacter);
        return toResponseDTO(updatedCharacter);
    }

    /**
     * Deletes a character from the database.
     * @param publicId The public UUID of the character to delete.
     * @return true if the character was found and deleted, false otherwise.
     */
    public boolean deleteCharacter(UUID publicId) {
        return characterRepository.findByPublicId(publicId).map(character -> {
            characterRepository.delete(character);
            return true;
        }).orElse(false);
    }

    /**
     * Retrieves a single character by their public UUID.
     * @param publicId The public UUID of the character.
     * @return An Optional containing the CharacterResponseDTO if found, otherwise empty.
     */
    public CharacterResponseDTO getCharacterByPublicId(UUID publicId) {
        return characterRepository.findByPublicId(publicId)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    /**
     * Private helper method to convert a CharacterModel entity to a CharacterResponseDTO.
     * This abstracts the mapping logic and controls what data is exposed to the client.
     * @param character The CharacterModel entity.
     * @return The mapped CharacterResponseDTO.
     */
    private CharacterResponseDTO toResponseDTO(CharacterModel character) {
        CharacterResponseDTO dto = new CharacterResponseDTO();
        dto.setId(character.getPublicId());
        dto.setName(character.getName());
        dto.setEmail(character.getEmail());
        dto.setAge(character.getAge());
        dto.setFaction(character.getFaction());
        dto.setRank(character.getRank());
        dto.setHomeland(character.getHomeland());
        dto.setImageUrl(character.getImageUrl());

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

    /**
     * Private helper method to validate if a given rank is valid for a given faction.
     * Throws IllegalArgumentException if the validation fails.
     * @param faction The character's faction.
     * @param rank The character's rank.
     */
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
                case CRIMINAL_UNDERWORLD:
                    CriminalUnderworldRank.valueOf(upperRank);
                    break;
                case GALACTIC_REPUBLIC:
                    GalacticRepublicRank.valueOf(upperRank);
                    break;
                case UNAFFILIATED:
                    Unaffiliated.valueOf(upperRank);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown faction provided: " + faction);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rank '" + rank + "' is not valid for faction '" + faction + "'.");
        }
    }
}
