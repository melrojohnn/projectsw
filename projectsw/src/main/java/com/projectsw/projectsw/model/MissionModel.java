package com.projectsw.projectsw.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missions")
public class MissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String rank;

    @OneToMany(mappedBy = "mission")
    private List<CharacterModel> character;



}
