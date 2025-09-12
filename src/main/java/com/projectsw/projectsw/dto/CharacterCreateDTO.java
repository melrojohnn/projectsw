package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.Faction;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object for creating or updating a character.
 * This class defines the structure of the data expected from the client in the request body.
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
    private String imageUrl;
    private UUID missionId; // The public ID of the mission to assign to the character.
}
