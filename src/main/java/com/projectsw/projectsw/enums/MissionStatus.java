package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MissionStatus {
    PENDING("Pending", "The mission has been planned but not yet started."),
    IN_PROGRESS("In Progress", "The mission is currently active and operatives are engaged."),
    COMPLETED("Completed", "The mission objectives were successfully achieved."),
    FAILED("Failed", "The mission objectives were not met.");

    private final String displayName;
    private final String description;

    MissionStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
