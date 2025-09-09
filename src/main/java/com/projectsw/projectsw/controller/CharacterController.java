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

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCharacter(@RequestBody CharacterCreateDTO characterDTO) {
        CharacterResponseDTO createdCharacter = characterService.createCharacter(characterDTO);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Character created successfully.");
        Map<String, Object> characterData = new LinkedHashMap<>();
        characterData.put("id", createdCharacter.getId()); // **CORRIGIDO:** Chama getId() no DTO
        characterData.put("name", createdCharacter.getName());
        response.put("data", characterData);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CharacterResponseDTO> getCharacterById(@PathVariable UUID id) {
        CharacterResponseDTO character = characterService.getCharacterByPublicId(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCharacterByID(@PathVariable UUID id, @RequestBody CharacterCreateDTO characterDTO){
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(id, characterDTO);
        if (updatedCharacter == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Character updated successfully.");
        Map<String, Object> characterData = new LinkedHashMap<>();
        characterData.put("id", updatedCharacter.getId()); // **CORRIGIDO:** Chama getId() no DTO
        characterData.put("name", updatedCharacter.getName());
        response.put("data", characterData);
        return ResponseEntity.ok(response);
    }

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