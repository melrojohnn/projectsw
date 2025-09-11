package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LightsideRank {
    PADAWAN("Padawan", "A Jedi apprentice, formally training under a Jedi Knight or Master."),
    KNIGHT("Jedi Knight", "A full member of the Jedi Order, having completed their trials."),
    MASTER("Jedi Master", "A powerful and wise Jedi who has trained a Padawan to Knighthood."),
    GRAND_MASTER("Jedi Grand Master", "The recognized leader of the entire Jedi Order.");

    private final String displayName;
    private final String description;

    LightsideRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
