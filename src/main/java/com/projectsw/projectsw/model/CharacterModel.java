package com.projectsw.projectsw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projectsw.projectsw.enums.Faction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a character in the Star Wars universe.
 * This is the core entity for managing individuals, their affiliations, and missions.
 */
@Entity
@Table(name = "tb_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterModel {

    /**
     * The internal, auto-incrementing primary key for database operations.
     * This is not exposed to the public API.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The public-facing, unique identifier for the character.
     * This is a UUID to prevent exposing internal database IDs in the API.
     */
    @Column(unique = true, nullable = false, updatable = false)
    private UUID publicId = UUID.randomUUID();

    /**
     * The name of the character.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * The unique email address of the character.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The age of the character.
     */
    private int age;

    /**
     * The character's primary faction allegiance.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Faction faction;

    /**
     * The character's rank within their faction.
     */
    @Column(nullable = false)
    private String rank;

    /**
     * The character's planet of origin.
     */
    private String homeland;

    /**
     * A URL pointing to an image of the character.
     */
    @Column(length = 1024)
    private String imageUrl;

    /**
     * The mission this character is currently assigned to.
     * This is a many-to-one relationship, as many characters can be on one mission.
     * JsonBackReference is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mission_id")
    @JsonBackReference
    private MissionModel mission;

    /**
     * Ensures that a publicId is generated before the entity is first saved to the database.
     */
    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
