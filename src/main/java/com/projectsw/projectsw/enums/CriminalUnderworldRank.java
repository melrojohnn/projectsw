package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CriminalUnderworldRank {
    CRIME_LORD("Crime Lord", "The leader of a major criminal organization."),
    ENFORCER("Enforcer", "A high-ranking operative responsible for enforcing the Crime Lord's will."),
    CAPO("Capo", "A captain or lieutenant within the syndicate."),
    THUG("Thug", "A low-level enforcer or soldier.");

    private final String displayName;
    private final String description;

    CriminalUnderworldRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
