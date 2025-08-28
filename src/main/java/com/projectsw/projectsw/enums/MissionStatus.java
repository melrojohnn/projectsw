package com.projectsw.projectsw.enums;

/**
 * Represents the possible statuses of a mission.
 */
public enum MissionStatus {
    PENDING,      // The mission has been planned but not started
    IN_PROGRESS,  // The mission is currently active
    COMPLETED,    // The mission was successfully completed
    FAILED        // The mission failed
}
