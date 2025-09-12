package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.Faction;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object for sending detailed character data back to the client.
 * This class defines the structure of a character as it is exposed through the API,
 * including nested objects for related data like missions.
 */
@Getter
@Setter
public class CharacterResponseDTO {
    private UUID id; // The public UUID of the character.
    private String name;
    private String email;
    private int age;
    private Faction faction;
    private String rank;
    private String homeland;
    private String imageUrl;
    private MissionSummaryDTO mission;
}
