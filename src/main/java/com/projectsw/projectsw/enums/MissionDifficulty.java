package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MissionDifficulty {
    BEGINNER("Beginner", "For absolute novices. Low risk."),
    EASY("Easy", "Requires basic skills. Minimal threat."),
    INTERMEDIATE("Intermediate", "Requires a competent team. Moderate risk."),
    ADVANCED("Advanced", "For seasoned veterans. High risk and complex objectives."),
    EXPERT("Expert", "Requires specialized skills and top-tier operatives. Extreme risk.");

    private final String displayName;
    private final String description;

    MissionDifficulty(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
