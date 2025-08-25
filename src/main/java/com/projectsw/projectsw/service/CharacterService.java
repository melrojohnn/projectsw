package com.projectsw.projectsw.service;

import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing Character entities.
 * This class contains the business logic for character operations.
 */
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    // Constructor-based dependency injection
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /**
     * Retrieves a list of all characters from the database.
     * @return a list of CharacterModel objects.
     */
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }

    /**
     * Creates and saves a new character to the database.
     * It ensures that a new entity is always created by setting the ID to null.
     * This prevents accidental updates via the create endpoint.
     * @param character The CharacterModel object to be created.
     * @return The saved CharacterModel object with its new ID.
     */
    public CharacterModel createCharacter(CharacterModel character) {
        // Ensure the ID is null to force the creation of a new entity
        character.setId(null);
        return characterRepository.save(character);
    }

    /**
     * Updates an existing character in the database.
     * @param id The ID of the character to update.
     * @param updatedCharacter The CharacterModel object with the new data.
     * @return The updated CharacterModel object, or null if the character was not found.
     */
    public CharacterModel updateCharacter(Long id, CharacterModel updatedCharacter) {
        // First, find the existing character by its ID
        CharacterModel existingCharacter = characterRepository.findById(id).orElse(null);

        // If the character exists, update its fields
        if (existingCharacter != null) {
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setEmail(updatedCharacter.getEmail());
            existingCharacter.setAge(updatedCharacter.getAge());
            existingCharacter.setHomeland(updatedCharacter.getHomeland());
            // Save the updated character back to the database
            return characterRepository.save(existingCharacter);
        } else {
            // Return null if no character with the given ID was found
            return null;
        }
    }

    /**
     * Deletes a character from the database by its ID.
     * @param id The ID of the character to be deleted.
     */
    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    /**
     * Finds and retrieves a single character by its ID.
     * @param id The ID of the character to find.
     * @return The CharacterModel object, or null if not found.
     */
    public CharacterModel getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }
}
