package com.projectsw.projectsw.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAuthority() {
        return this.name(); // e.g., "ROLE_ADMIN"
    }
}
