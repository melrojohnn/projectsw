package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.Faction;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for receiving data to create a new character.
 * Contains only the fields a user should provide.
 */
@Getter
@Setter
public class CharacterCreateDTO {
    private String name;
    private String email;
    private int age;
    private Faction faction;
    private String rank;
    private String homeland;
    private UUID missionId; // ID of the mission to assign the character to
}
