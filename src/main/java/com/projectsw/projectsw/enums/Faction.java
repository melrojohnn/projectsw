package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Faction {
    REBEL_ALLIANCE("Rebel Alliance", "The Alliance to Restore the Republic, a resistance movement formed to combat the Galactic Empire."),
    GALACTIC_EMPIRE("Galactic Empire", "The autocratic government that replaced the Galactic Republic in the aftermath of the Clone Wars."),
    JEDI_ORDER("Jedi Order", "An ancient monastic peacekeeping organization unified by its belief and observance of the Force."),
    SITH("Sith", "An ancient order of Force-wielders devoted to the dark side of the Force."),
    DROID_ARMY("Droid Army", "The military forces of the Confederacy of Independent Systems, composed primarily of battle droids."),
    CRIMINAL_UNDERWORLD("Criminal Underworld", "A collective of powerful crime syndicates, including Hutts, Pykes, and Black Sun."),
    GALACTIC_REPUBLIC("Galactic Republic", "The democratic government that governed the galaxy for millennia before the rise of the Empire."),
    UNAFFILIATED("Unaffiliated", "Individuals not aligned with any major faction, such as bounty hunters, smugglers, or mercenaries.");

    private final String displayName;
    private final String description;

    Faction(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
