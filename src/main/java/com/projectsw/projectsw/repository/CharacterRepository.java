package com.projectsw.projectsw.repository;

import com.projectsw.projectsw.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
}
