package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.dto.MissionCreateDTO;
import com.projectsw.projectsw.dto.MissionResponseDTO;
import com.projectsw.projectsw.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
     * @return A clear response with the created mission's summary.
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMission(@RequestBody MissionCreateDTO missionDTO) {
        MissionResponseDTO createdMission = missionService.createMission(missionDTO);

        // Build a custom response map
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Mission created successfully.");

        Map<String, Object> missionData = new LinkedHashMap<>();
        missionData.put("id", createdMission.getId());
        missionData.put("title", createdMission.getTitle());
        response.put("data", missionData);

        return ResponseEntity.status(201).body(response);
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
     * @return A clear response with the updated mission's summary.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMissionByID(@PathVariable UUID id, @RequestBody MissionCreateDTO missionDTO){
        MissionResponseDTO updatedMission = missionService.updateMission(id, missionDTO);

        if (updatedMission == null) {
            return ResponseEntity.notFound().build();
        }

        // Build a custom response map
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Mission updated successfully.");

        Map<String, Object> missionData = new LinkedHashMap<>();
        missionData.put("id", updatedMission.getId());
        missionData.put("title", updatedMission.getTitle());
        response.put("data", missionData);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to delete a mission by its public ID.
     * @param id The public UUID of the mission to delete.
     * @return A clear success message or 404 Not Found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMissionByID(@PathVariable UUID id){
        boolean isDeleted = missionService.deleteMission(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        // Build a custom response map
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Mission with ID " + id + " deleted successfully.");

        return ResponseEntity.ok(response);
    }
}
