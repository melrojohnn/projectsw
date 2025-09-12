package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionDifficulty;
import com.projectsw.projectsw.enums.MissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for sending detailed mission data back to the client.
 * This class defines the structure of a mission as it is exposed through the API,
 * including a list of members assigned to it.
 */
@Getter
@Setter
public class MissionResponseDTO {
    private UUID id; // The public UUID of the mission.
    private String title;
    private String description;
    private MissionStatus status;
    private MissionDifficulty rank; // Represents the mission's difficulty.
    private List<CharacterSummaryDTO> members; // A list of characters assigned to the mission.
}
