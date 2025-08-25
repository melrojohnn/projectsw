package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Character entities.
 * Exposes endpoints for CRUD operations on characters.
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Endpoint to create a new character.
     * HTTP Method: POST
     * URL: /character/create
     * @param character The character data sent in the request body.
     * @return The newly created character.
     */
    @PostMapping("/create")
    public CharacterModel createCharacter(@RequestBody CharacterModel character) {
        return characterService.createCharacter(character);
    }

    /**
     * Endpoint to retrieve all characters.
     * HTTP Method: GET
     * URL: /character/all
     * @return A list of all characters.
     */
    @GetMapping("/all")
    public List<CharacterModel> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    /**
     * Endpoint to retrieve a single character by its ID.
     * HTTP Method: GET
     * URL: /character/list/{id}
     * @param id The ID of the character, passed in the URL path.
     * @return A ResponseEntity containing the character if found, or 404 Not Found.
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable Long id) {
        CharacterModel character = characterService.getCharacterById(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to update an existing character.
     * HTTP Method: PUT
     * URL: /character/update/{id}
     * @param id The ID of the character to update.
     * @param characterDetails The new character data from the request body.
     * @return A ResponseEntity containing the updated character, or 404 Not Found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CharacterModel> updateCharacterByID(@PathVariable Long id, @RequestBody CharacterModel characterDetails){
        CharacterModel updatedCharacter = characterService.updateCharacter(id, characterDetails);
        if (updatedCharacter != null) {
            return ResponseEntity.ok(updatedCharacter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a character by its ID.
     * HTTP Method: DELETE
     * URL: /character/delete/{id}
     * @param id The ID of the character to delete.
     * @return A ResponseEntity with a success message or 404 Not Found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCharacterByID(@PathVariable Long id){
        // We can add a check here to see if the character exists before deleting
        characterService.deleteCharacter(id);
        return ResponseEntity.ok("Character deleted successfully");
    }
}
