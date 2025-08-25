package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {


    @PostMapping("/create")
    public String createCharacter() {
        return "Character created successfully";
    }

    @GetMapping("/all")
    public String getAllCharacters() {
        return "Show all characters";
    }

    @PutMapping("/update/{id}")
    public String updateCharacterByID(){
        return "Character updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCharacterByID(){
        return "Character deleted successfully";
    }
}
