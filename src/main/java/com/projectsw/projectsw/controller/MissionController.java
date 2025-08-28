package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.dto.MissionCreateDTO;
import com.projectsw.projectsw.dto.MissionResponseDTO;
import com.projectsw.projectsw.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for managing Mission entities.
 * Exposes endpoints for CRUD operations on missions using public UUIDs and DTOs.
 */
@RestController
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    /**
     * Endpoint to create a new mission.
     * @param missionDTO The mission data sent in the request body.
     * @return The newly created mission data as a DTO.
     */
    @PostMapping("/create")
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionCreateDTO missionDTO) {
        MissionResponseDTO createdMission = missionService.createMission(missionDTO);
        return ResponseEntity.ok(createdMission);
    }

    /**
     * Endpoint to retrieve all missions.
     * @return A list of all missions as DTOs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<MissionResponseDTO>> getAllMissions() {
        List<MissionResponseDTO> missions = missionService.getAllMissions();
        return ResponseEntity.ok(missions);
    }

    /**
     * Endpoint to retrieve a single mission by its public ID.
     * @param id The public UUID of the mission.
     * @return A ResponseEntity containing the mission DTO if found, or 404 Not Found.
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<MissionResponseDTO> getMissionById(@PathVariable UUID id) {
        MissionResponseDTO mission = missionService.getMissionByPublicId(id);
        if (mission != null) {
            return ResponseEntity.ok(mission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to update an existing mission by its public ID.
     * @param id The public UUID of the mission to update.
     * @param missionDTO The new mission data from the request body.
     * @return A ResponseEntity containing the updated mission DTO, or 404 Not Found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<MissionResponseDTO> updateMissionByID(@PathVariable UUID id, @RequestBody MissionCreateDTO missionDTO){
        MissionResponseDTO updatedMission = missionService.updateMission(id, missionDTO);
        if (updatedMission != null) {
            return ResponseEntity.ok(updatedMission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a mission by its public ID.
     * @param id The public UUID of the mission to delete.
     * @return A ResponseEntity with a success message or 404 Not Found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMissionByID(@PathVariable UUID id){
        boolean isDeleted = missionService.deleteMission(id);
        if (isDeleted) {
            return ResponseEntity.ok("Mission with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
