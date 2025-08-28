package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.Faction;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for sending character data back to the client.
 * This controls what information is exposed in the API response.
 */
@Getter
@Setter
public class CharacterResponseDTO {
    private UUID id; // This will be the publicId
    private String name;
    private String email;
    private int age;
    private Faction faction;
    private String rank;
    private String homeland;
    private MissionSummaryDTO mission;
}
