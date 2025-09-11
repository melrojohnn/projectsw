package com.projectsw.projectsw.controller;

import com.projectsw.projectsw.dto.EnumDTO;
import com.projectsw.projectsw.enums.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A controller to provide data for frontend forms, such as dropdown options.
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    /**
     * Endpoint to fetch all necessary enum values for form dropdowns.
     * This provides a single source of truth for the frontend.
     * @return A ResponseEntity containing a map of all form options.
     */
    @GetMapping("/form-options")
    public ResponseEntity<Map<String, Object>> getFormOptions() {
        List<EnumDTO> factions = toDtoList(Faction.values());
        List<EnumDTO> missionStatuses = toDtoList(MissionStatus.values());
        List<EnumDTO> missionDifficulties = toDtoList(MissionDifficulty.values());

        // Create the complex map for ranks based on faction
        Map<String, List<EnumDTO>> ranksByFaction = Map.of(
            Faction.REBEL_ALLIANCE.name(), toDtoList(RebelRank.values()),
            Faction.GALACTIC_EMPIRE.name(), toDtoList(EmpireRank.values()),
            Faction.JEDI_ORDER.name(), toDtoList(LightsideRank.values()),
            Faction.SITH.name(), toDtoList(DarksideRank.values()),
            Faction.DROID_ARMY.name(), toDtoList(DroidRank.values()),
            Faction.CRIMINAL_UNDERWORLD.name(), toDtoList(CriminalUnderworldRank.values()),
            Faction.GALACTIC_REPUBLIC.name(), toDtoList(GalacticRepublicRank.values()), // Add new faction
            Faction.UNAFFILIATED.name(), toDtoList(Unaffiliated.values())
        );

        // Combine everything into a single response object
        Map<String, Object> response = Map.of(
            "factions", factions,
            "missionStatuses", missionStatuses,
            "missionDifficulties", missionDifficulties,
            "ranksByFaction", ranksByFaction
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Generic helper method to convert any enum that has displayName and description
     * into a list of EnumDTOs.
     */
    private <T extends Enum<T>> List<EnumDTO> toDtoList(T[] values) {
        return Arrays.stream(values)
            .map(value -> {
                try {
                    String displayName = (String) value.getClass().getMethod("getDisplayName").invoke(value);
                    String description = (String) value.getClass().getMethod("getDescription").invoke(value);
                    return new EnumDTO(value.name(), displayName, description);
                } catch (Exception e) {
                    return new EnumDTO(value.name(), value.name(), "");
                }
            })
            .collect(Collectors.toList());
    }
}
