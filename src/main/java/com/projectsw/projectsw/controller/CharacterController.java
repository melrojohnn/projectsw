package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.dto.CharacterCreateDTO;
import com.projectsw.projectsw.dto.CharacterResponseDTO;
import com.projectsw.projectsw.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for handling character-related API requests.
 * All endpoints under this controller are prefixed with /character.
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Endpoint to create a new character.
     * @param characterDTO The character data sent in the request body.
     * @return A ResponseEntity with a custom success message and the created character's basic info.
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCharacter(@RequestBody CharacterCreateDTO characterDTO) {
        CharacterResponseDTO createdCharacter = characterService.createCharacter(characterDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Character created successfully.");
        Map<String, Object> characterData = new LinkedHashMap<>();
        characterData.put("id", createdCharacter.getId());
        characterData.put("name", createdCharacter.getName());
        response.put("data", characterData);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Endpoint to retrieve all characters.
     * @return A ResponseEntity containing a list of all characters.
     */
    @GetMapping("/all")
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    /**
     * Endpoint to retrieve a single character by their public UUID.
     * @param id The UUID of the character, passed as a path variable.
     * @return A ResponseEntity with the character data, or 404 Not Found if the character does not exist.
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

    /**
     * Endpoint to update an existing character.
     * @param id The UUID of the character to update.
     * @param characterDTO The new character data sent in the request body.
     * @return A ResponseEntity with a success message, or 404 Not Found if the character does not exist.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCharacterByID(@PathVariable UUID id, @RequestBody CharacterCreateDTO characterDTO){
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(id, characterDTO);
        if (updatedCharacter == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Character updated successfully.");
        Map<String, Object> characterData = new LinkedHashMap<>();
        characterData.put("id", updatedCharacter.getId());
        characterData.put("name", updatedCharacter.getName());
        response.put("data", characterData);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to delete a character.
     * @param id The UUID of the character to delete.
     * @return A ResponseEntity with a success message, or 404 Not Found if the character does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCharacterByID(@PathVariable UUID id){
        boolean isDeleted = characterService.deleteCharacter(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Character with ID " + id + " deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
