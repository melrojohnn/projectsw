package com.projectsw.projectsw.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_members")
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;

    //@ManytoOne once one character per mission
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private MissionModel mission;

    public CharacterModel() {

    }

    public CharacterModel(String name, String email, int age, MissionModel mission) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.mission = mission;

    }

    public MissionModel getMission() {
        return mission;
    }

    public void setMission(MissionModel mission) {
        this.mission = mission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
