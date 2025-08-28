package com.projectsw.projectsw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A simplified DTO representing a summary of a character.
 * Used to avoid circular dependencies in other DTOs.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSummaryDTO {
    private UUID id; // The publicId
    private String name;
    private String rank;
}
