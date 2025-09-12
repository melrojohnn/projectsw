package com.projectsw.projectsw.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectsw.projectsw.enums.MissionDifficulty;
import com.projectsw.projectsw.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Represents a mission that characters can be assigned to.
 */
@Entity
@Table(name = "tb_missions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MissionModel {

    /**
     * The internal, auto-incrementing primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The public-facing, unique identifier for the mission.
     */
    @Column(unique = true, nullable = false, updatable = false)
    private UUID publicId = UUID.randomUUID();

    /**
     * The title of the mission.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * A detailed description of the mission objectives.
     */
    @Column(length = 1024)
    private String description;

    /**
     * The current status of the mission (e.g., PENDING, IN_PROGRESS).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    /**
     * The difficulty level of the mission, represented by the rank.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionDifficulty rank;

    /**
     * The list of characters assigned to this mission.
     * This is the inverse side of the many-to-one relationship in CharacterModel.
     * JsonManagedReference is used to handle the "forward" part of the reference, preventing serialization issues.
     */
    @OneToMany(mappedBy = "mission")
    @JsonManagedReference
    private List<CharacterModel> members;

    /**
     * Ensures that a publicId is generated before the entity is first saved.
     */
    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
