package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionDifficulty;
import com.projectsw.projectsw.enums.MissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * DTO for sending mission data back to the client.
 */
@Getter
@Setter
public class MissionResponseDTO {
    private UUID id; // publicId
    private String title;
    private String description;
    private MissionStatus status;
    private MissionDifficulty rank; // difficulty
    private List<CharacterSummaryDTO> members;
}
