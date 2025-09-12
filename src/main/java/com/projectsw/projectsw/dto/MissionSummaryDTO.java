package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A simplified Data Transfer Object representing a summary of a mission.
 * This is typically used for nested representations, such as showing the assigned mission
 * within a character's details, to provide essential information without causing circular serialization issues.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionSummaryDTO {
    private UUID id; // The public UUID of the mission.
    private String title;
    private String description;
    private MissionStatus status;
}
