package com.projectsw.projectsw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missions")
@NoArgsConstructor
@AllArgsConstructor
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<CharacterModel> getCharacter() {
        return character;
    }

    public void setCharacter(List<CharacterModel> character) {
        this.character = character;
    }
}
