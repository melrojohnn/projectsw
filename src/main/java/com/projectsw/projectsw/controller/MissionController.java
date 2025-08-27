package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Mission entities.
 * Exposes endpoints for CRUD operations on missions.
 */
@RestController
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    /**
     * Endpoint to create a new mission.
     * HTTP Method: POST
     * URL: /mission/create
     * @param mission The mission data sent in the request body.
     * @return The newly created mission.
     */
    @PostMapping("/create")
    public MissionModel createMission(@RequestBody MissionModel mission) {
        return missionService.createMission(mission);
    }

    /**
     * Endpoint to retrieve all missions.
     * HTTP Method: GET
     * URL: /mission/all
     * @return A list of all missions.
     */
    @GetMapping("/all")
    public List<MissionModel> getAllMissions() {
        return missionService.getAllMissions();
    }

    /**
     * Endpoint to retrieve a single mission by its ID.
     * HTTP Method: GET
     * URL: /mission/list/{id}
     * @param id The ID of the mission, passed in the URL path.
     * @return A ResponseEntity containing the mission if found, or 404 Not Found.
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<MissionModel> getMissionById(@PathVariable Long id) {
        MissionModel mission = missionService.getMissionById(id);
        if (mission != null) {
            return ResponseEntity.ok(mission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to update an existing mission.
     * HTTP Method: PUT
     * URL: /mission/update/{id}
     * @param id The ID of the mission to update.
     * @param missionDetails The new mission data from the request body.
     * @return A ResponseEntity containing the updated mission, or 404 Not Found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<MissionModel> updateMissionByID(@PathVariable Long id, @RequestBody MissionModel missionDetails){
        MissionModel updatedMission = missionService.updateMission(id, missionDetails);
        if (updatedMission != null) {
            return ResponseEntity.ok(updatedMission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a mission by its ID.
     * HTTP Method: DELETE
     * URL: /mission/delete/{id}
     * @param id The ID of the mission to delete.
     * @return A ResponseEntity with a success message.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMissionByID(@PathVariable Long id){
        missionService.deleteMission(id);
        return ResponseEntity.ok("Mission deleted successfully");
    }
}
