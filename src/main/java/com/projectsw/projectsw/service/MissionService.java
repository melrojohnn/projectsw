package com.projectsw.projectsw.service;

import com.projectsw.projectsw.model.CharacterModel;

import com.projectsw.projectsw.model.MissionModel;
import com.projectsw.projectsw.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public MissionModel createMission(MissionModel mission) {
        mission.setId(null);
        return missionRepository.save(mission);
    }

    public List<MissionModel> getAllMissions() {
        return missionRepository.findAll();
    }

    public MissionModel getMissionById(Long id) {
        return missionRepository.findById(id).orElse(null);
    }

    public MissionModel updateMission(Long id, MissionModel updatedMission) {
        MissionModel existingMission = missionRepository.findById(id).orElse(null);

        if (existingMission != null) {
            existingMission.setTitle(updatedMission.getTitle());
            existingMission.setDescription(updatedMission.getDescription());
            existingMission.setStatus(updatedMission.getStatus());
            existingMission.setRank(updatedMission.getRank());
            return missionRepository.save(existingMission);
        } else {
            return null;
        }
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}


