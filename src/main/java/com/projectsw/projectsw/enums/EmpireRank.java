package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum EmpireRank {
    CADET("Cadet", "A trainee in an Imperial academy."),
    TROOPER("Trooper", "A standard infantry soldier of the Galactic Empire."),
    CAPTAIN("Captain", "A junior officer rank, often commanding a company of troops or a small vessel."),
    COMMANDER("Commander", "A field officer responsible for leading larger units or starships."),
    MOFF("Moff", "A sector governor in the Galactic Empire."),
    GRAND_MOFF("Grand Moff", "A governor of an Oversector, one of the highest-ranking Imperial officials.");

    private final String displayName;
    private final String description;

    EmpireRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
