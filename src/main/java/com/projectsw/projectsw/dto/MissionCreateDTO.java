package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionDifficulty;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating or updating a mission.
 * This class defines the structure of the data expected from the client in the request body.
 */
@Getter
@Setter
public class MissionCreateDTO {
    private String title;
    private String description;
    private MissionDifficulty rank; // Represents the mission's difficulty
}
