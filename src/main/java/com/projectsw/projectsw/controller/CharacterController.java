package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public ResponseEntity<List<CharacterModel>> getAllCharacters() {
        List<CharacterModel> characters = characterRepository.findAll();
        return ResponseEntity.ok(characters);
    }
}
