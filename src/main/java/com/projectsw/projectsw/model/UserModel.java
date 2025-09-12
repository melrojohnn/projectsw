package com.projectsw.projectsw.model;

import com.projectsw.projectsw.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a user of the application for authentication and authorization purposes.
 * This entity implements Spring Security's UserDetails interface, allowing it to be seamlessly
 * integrated with the security framework.
 */
@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel implements UserDetails {

    /**
     * The unique identifier for the user, generated automatically as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The username, used for login. It must be unique.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The user's password, stored as a BCrypt hash.
     * It is never stored in plain text.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The set of roles assigned to the user, which determine their permissions.
     * This is eagerly fetched as it's fundamental for authorization.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Set<Role> roles;

    // --- UserDetails Interface Methods ---

    /**
     * Returns the authorities (roles) granted to the user.
     * Spring Security uses this to perform authorization checks.
     * @return A collection of roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     * @return true if the user's account is valid (non-expired), false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // For now, accounts never expire.
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // For now, accounts are never locked.
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
     * @return true if the user's credentials are valid (non-expired), false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // For now, credentials never expire.
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true; // For now, users are always enabled.
    }
}
