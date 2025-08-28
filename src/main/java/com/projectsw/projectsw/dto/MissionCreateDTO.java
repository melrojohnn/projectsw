package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionDifficulty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for receiving data to create a new mission.
 */
@Getter
@Setter
public class MissionCreateDTO {
    private String title;
    private String description;
    private MissionDifficulty rank; // This is the difficulty
}
