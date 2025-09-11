package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DarksideRank {
    ACOLYTE("Acolyte", "An apprentice learning the ways of the dark side."),
    SITH_APPRENTICE("Sith Apprentice", "A formal apprentice to a Sith Lord, operating under the Rule of Two."),
    SITH_LORD("Sith Lord", "A master of the dark side, often holding significant power."),
    DARTH("Darth", "The highest title for a Sith Lord, signifying immense power and authority.");

    private final String displayName;
    private final String description;

    DarksideRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
