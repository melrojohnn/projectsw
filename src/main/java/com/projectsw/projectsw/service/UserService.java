package com.projectsw.projectsw.service;

import com.projectsw.projectsw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This service is responsible for loading user-specific data.
 * It implements Spring Security's UserDetailsService, which is the core interface
 * for user data retrieval. It acts as a bridge between the UserRepository and the
 * Spring Security authentication mechanism.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual authentication process,
     * the AuthenticationManager calls this method to obtain user details.
     * @param username The username identifying the user whose data is required.
     * @return A fully populated user record (never null).
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
