package com.projectsw.projectsw.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Unaffiliated {

    BOUNTY_HUNTER("Bounty Hunter", "A professional hired to capture or eliminate targets."),
    MANDALORIAN("Mandalorian", "A member of a creed-based culture of warriors and bounty hunters."),
    SMUGGLER("Smuggler", "An independent pilot who transports illicit goods."),
    MERCENARY("Mercenary", "A soldier for hire, loyal only to their client."),
    PIRATE("Pirate", "An outlaw who operates from a starship to raid and plunder."),
    SCOUT("Scout", "An explorer or reconnaissance specialist operating in uncharted territories."),
    REBEL_UNAFFILIATED("Rebel (Unaffiliated)", "Sympathetic to the Rebel cause but not an official member."),
    GALACTIC_EMPIRE_UNAFFILIATED("Galactic Empire (Unaffiliated)", "Works with the Empire but is not formally enlisted."),
    JEDI_ORDER_UNAFFILIATED("Jedi Order (Unaffiliated)", "Follows Jedi teachings but is not part of the official Order."),
    OVERLORD("Overlord", "Three powerful beings who control and balance the Force.");

    private final String displayName;
    private final String description;

    Unaffiliated(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @JsonValue
    public String getKey() {
        return this.name();
    }
}
