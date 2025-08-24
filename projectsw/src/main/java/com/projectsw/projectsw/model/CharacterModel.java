package com.projectsw.projectsw.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_members")
@NoArgsConstructor
@AllArgsConstructor
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(unique = true)
    private String email;
    @Column(name = "age")
    private int age;

    //@ManytoOne once one character per mission
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private MissionModel mission;

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

    public MissionModel getMission() {
        return mission;
    }

    public void setMission(MissionModel mission) {
        this.mission = mission;
    }
}