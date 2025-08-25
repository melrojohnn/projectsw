package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionRepository missionRepository;

    @GetMapping
    public ResponseEntity<List<MissionModel>> getAllMissions() {
        List<MissionModel> missions = missionRepository.findAll();
        return ResponseEntity.ok(missions);
    }
}
