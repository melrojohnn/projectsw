package com.projectsw.projectsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A generic DTO for representing an enum value with its details.
 */
@Getter
@Setter
@AllArgsConstructor
public class EnumDTO {
    private String key;          // e.g., "REBEL_ALLIANCE"
    private String displayName;  // e.g., "Rebel Alliance"
    private String description;  // e.g., "The Alliance to Restore the Republic..."
}
