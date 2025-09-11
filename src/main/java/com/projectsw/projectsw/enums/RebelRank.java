package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RebelRank {
    RECRUIT("Recruit", "A new member of the Rebel Alliance."),
    PRIVATE("Private", "A standard soldier in the Rebel infantry."),
    SERGEANT("Sergeant", "A non-commissioned officer leading a small squad."),
    LIEUTENANT("Lieutenant", "A junior commissioned officer."),
    CAPTAIN("Captain", "An officer commanding a company or a small starship."),
    COMMANDER("Commander", "A senior officer, often in charge of a starfighter squadron or a ground unit."),
    GENERAL("General", "A high-ranking officer commanding large-scale military operations.");

    private final String displayName;
    private final String description;

    RebelRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
