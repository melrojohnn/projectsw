package com.projectsw.projectsw.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private int age;
    private String rank;
    private String homeland;


    @ManyToOne
    @JoinColumn(name = "mission_id")
    @JsonBackReference
    private MissionModel mission;
}