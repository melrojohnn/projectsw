package com.projectsw.projectsw.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projectsw.projectsw.enums.Faction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal ID for database performance (joins, etc.)

    @Column(unique = true, nullable = false, updatable = false)
    private UUID publicId = UUID.randomUUID(); // Public-facing ID for APIs

    private String name;

    @Column(unique = true)
    private String email;

    private int age;

    @Enumerated(EnumType.STRING)
    private Faction faction;

    private String rank;
    private String homeland;

    @Column(length = 1024) // Allow for longer URLs
    private String imageUrl;


    @ManyToOne(fetch = FetchType.EAGER) // ** Force eager loading to fix the issue **
    @JoinColumn(name = "mission_id")
    @JsonBackReference
    private MissionModel mission;

    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
