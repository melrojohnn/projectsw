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
 * A REST controller dedicated to providing frontend UI components with the necessary data
 * to build dynamic forms, such as dropdown options. This centralizes the data source for the UI.
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    /**
     * Endpoint to fetch all necessary enum values for form dropdowns.
     * This provides a single source of truth for the frontend, ensuring that any changes
     * in the backend enums are automatically reflected in the UI without needing to
     * change frontend code.
     *
     * @return A ResponseEntity containing a map structured for easy consumption by the frontend.
     */
    @GetMapping("/form-options")
    public ResponseEntity<Map<String, Object>> getFormOptions() {
        // Convert all relevant enums to a list of DTOs.
        List<EnumDTO> factions = toDtoList(Faction.values());
        List<EnumDTO> missionStatuses = toDtoList(MissionStatus.values());
        List<EnumDTO> missionDifficulties = toDtoList(MissionDifficulty.values());

        // Create a map that associates each faction's key with its list of corresponding ranks.
        Map<String, List<EnumDTO>> ranksByFaction = Map.of(
            Faction.REBEL_ALLIANCE.name(), toDtoList(RebelRank.values()),
            Faction.GALACTIC_EMPIRE.name(), toDtoList(EmpireRank.values()),
            Faction.JEDI_ORDER.name(), toDtoList(LightsideRank.values()),
            Faction.SITH.name(), toDtoList(DarksideRank.values()),
            Faction.DROID_ARMY.name(), toDtoList(DroidRank.values()),
            Faction.CRIMINAL_UNDERWORLD.name(), toDtoList(CriminalUnderworldRank.values()),
            Faction.GALACTIC_REPUBLIC.name(), toDtoList(GalacticRepublicRank.values()),
            Faction.UNAFFILIATED.name(), toDtoList(Unaffiliated.values())
        );

        // Combine all data into a single, well-structured response object.
        Map<String, Object> response = Map.of(
            "factions", factions,
            "missionStatuses", missionStatuses,
            "missionDifficulties", missionDifficulties,
            "ranksByFaction", ranksByFaction
        );

        return ResponseEntity.ok(response);
    }

    /**
     * A generic helper method to convert an array of any enum into a list of EnumDTOs.
     * This method uses reflection to dynamically call `getDisplayName()` and `getDescription()`
     * on each enum constant, assuming they follow the established convention.
     *
     * @param values The array of enum values (e.g., `Faction.values()`).
     * @param <T> The enum type.
     * @return A list of EnumDTOs, where each DTO contains the key, display name, and description.
     */
    private <T extends Enum<T>> List<EnumDTO> toDtoList(T[] values) {
        return Arrays.stream(values)
            .map(value -> {
                try {
                    String displayName = (String) value.getClass().getMethod("getDisplayName").invoke(value);
                    String description = (String) value.getClass().getMethod("getDescription").invoke(value);
                    return new EnumDTO(value.name(), displayName, description);
                } catch (Exception e) {
                    // This fallback should not be reached if the enum convention is followed.
                    return new EnumDTO(value.name(), value.name(), "");
                }
            })
            .collect(Collectors.toList());
    }
}
