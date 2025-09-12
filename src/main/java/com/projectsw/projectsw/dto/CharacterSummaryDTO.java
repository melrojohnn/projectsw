package com.projectsw.projectsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A simplified Data Transfer Object representing a summary of a character.
 * This is typically used for nested representations, such as listing members within a mission,
 * to provide essential information without causing circular serialization issues.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSummaryDTO {
    private UUID id; // The public UUID of the character.
    private String name;
    private String rank;
}
