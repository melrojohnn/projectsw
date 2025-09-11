package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DroidRank {
    PROTOCOL_DROID("Protocol Droid", "Specializes in translation, etiquette, and human-cyborg relations."),
    ASTROMECH_DROID("Astromech Droid", "A versatile, multipurpose droid used for starship maintenance and navigation."),
    BATTLE_DROID("Battle Droid", "A standard infantry soldier used by the Separatist army."),
    ASSASSIN_DROID("Assassin Droid", "A highly illegal droid programmed for infiltration and assassination."),
    SUPREME_COMMANDER("Supreme Commander", "The highest military rank in the Separatist Droid Army.");

    private final String displayName;
    private final String description;

    DroidRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
