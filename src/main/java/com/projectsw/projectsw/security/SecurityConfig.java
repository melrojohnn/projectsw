package com.projectsw.projectsw.security;

import com.projectsw.projectsw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Main configuration class for Spring Security.
 * This class defines the security filter chain, password encoding, and other security-related beans.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the main security filter chain that applies to all HTTP requests.
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (Cross-Site Request Forgery) protection, as it's not needed for stateless REST APIs that use tokens.
            .csrf(csrf -> csrf.disable())
            // Set the session management policy to STATELESS, as we will not be using HTTP sessions.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Configure authorization rules for HTTP requests.
            .authorizeHttpRequests(authorize -> authorize
                // For now, permit access to all endpoints. This will be secured with JWT later.
                .anyRequest().permitAll()
            );
        return http.build();
    }

    /**
     * Provides the password encoder bean for the application.
     * BCrypt is the modern industry standard for hashing passwords securely.
     * @return The password encoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the AuthenticationManager as a Bean.
     * This manager is responsible for processing an authentication request.
     * We will inject this into our AuthController to handle the login process.
     * @param authenticationConfiguration The authentication configuration from Spring.
     * @return The configured AuthenticationManager.
     * @throws Exception if an error occurs.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
