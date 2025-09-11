package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GalacticRepublicRank {
    SENATOR("Senator", "A representative of a planet or sector in the Galactic Senate."),
    CHANCELLOR("Supreme Chancellor", "The elected leader of the Galactic Republic."),
    VICE_CHAIR("Vice Chair", "The second-in-command to the Supreme Chancellor."),
    AMBASSADOR("Ambassador", "A diplomat representing the Republic in foreign affairs."),
    REPRESENTATIVE("Representative", "A delegate serving in the Galactic Senate.");

    private final String displayName;
    private final String description;

    GalacticRepublicRank(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
