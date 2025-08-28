package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.dto.CharacterCreateDTO;
import com.projectsw.projectsw.dto.CharacterResponseDTO;
import com.projectsw.projectsw.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for managing Character entities.
 * Exposes endpoints for CRUD operations on characters using public UUIDs.
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Endpoint to create a new character.
     * @param characterDTO The character data sent in the request body.
     * @return The newly created character data as a DTO.
     */
    @PostMapping("/create")
    public ResponseEntity<CharacterResponseDTO> createCharacter(@RequestBody CharacterCreateDTO characterDTO) {
        CharacterResponseDTO createdCharacter = characterService.createCharacter(characterDTO);
        return ResponseEntity.ok(createdCharacter);
    }

    /**
     * Endpoint to retrieve all characters.
     * @return A list of all characters as DTOs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    /**
     * Endpoint to retrieve a single character by its public ID.
     * @param id The public UUID of the character.
     * @return A ResponseEntity containing the character DTO if found, or 404 Not Found.
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<CharacterResponseDTO> getCharacterById(@PathVariable UUID id) {
        CharacterResponseDTO character = characterService.getCharacterByPublicId(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//0c5eae90-fbb8-4714-9f52-21e77bddf5fb
    /**
     * Endpoint to update an existing character by its public ID.
     * @param id The public UUID of the character to update.
     * @param characterDTO The new character data from the request body.
     * @return A ResponseEntity containing the updated character DTO, or 404 Not Found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacterByID(@PathVariable UUID id, @RequestBody CharacterCreateDTO characterDTO){
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(id, characterDTO);
        if (updatedCharacter != null) {
            return ResponseEntity.ok(updatedCharacter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a character by its public ID.
     * @param id The public UUID of the character to delete.
     * @return A ResponseEntity with a success message or 404 Not Found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCharacterByID(@PathVariable UUID id){
        boolean isDeleted = characterService.deleteCharacter(id);
        if (isDeleted) {
            return ResponseEntity.ok("Character with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
