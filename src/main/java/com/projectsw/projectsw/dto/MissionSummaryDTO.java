package com.projectsw.projectsw.dto;

import com.projectsw.projectsw.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A simplified DTO representing a summary of a mission.
 * Used to avoid circular dependencies in other DTOs.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionSummaryDTO {
    private UUID id; // This will be the publicId
    private String title;
    private String description;
    private MissionStatus status;
}
