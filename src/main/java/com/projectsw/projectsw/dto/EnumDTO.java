package com.projectsw.projectsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A generic Data Transfer Object for representing a Java enum in a structured way for the frontend.
 * This allows the backend to be the single source of truth for dropdown options, including their
 * technical key, user-friendly display name, and a helpful description.
 */
@Getter
@Setter
@AllArgsConstructor
public class EnumDTO {
    /**
     * The technical key of the enum, corresponding to its constant name (e.g., "REBEL_ALLIANCE").
     * This is used as the value in HTML select options.
     */
    private String key;

    /**
     * The user-friendly name for display in the UI (e.g., "Rebel Alliance").
     */
    private String displayName;

    /**
     * A detailed description of the option, often used as a tooltip in the UI.
     */
    private String description;
}
