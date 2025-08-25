package com.projectsw.projectsw.service;

import com.projectsw.projectsw.model.CharacterModel;
import com.projectsw.projectsw.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    //List all characters
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }




}
