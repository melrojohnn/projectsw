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

@Entity
@Table(name = "tb_missions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal ID

    @Column(unique = true, nullable = false, updatable = false)
    private UUID publicId = UUID.randomUUID(); // Public-facing ID

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1024)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionDifficulty rank; // Represents the mission's difficulty

    @OneToMany(mappedBy = "mission")
    @JsonManagedReference
    private List<CharacterModel> members;

    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
