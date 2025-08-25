package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @PostMapping("/create")
    public String createMission() {
        return "Mission created successfully";
    }

    @GetMapping("/all")
    public String listAllMissions() {
        return "Show all missions";
    }

    @PutMapping("/update/{id}")
    public String updateMissionByID(){
        return "Mission updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMissionByID(){
        return "Mission deleted successfully";
    }

}
