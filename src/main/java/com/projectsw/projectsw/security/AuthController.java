package com.projectsw.projectsw.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller dedicated to handling authentication requests, such as user login.
 * All endpoints under this controller are prefixed with /auth.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Endpoint for user login.
     * In the future, this will receive user credentials, validate them using the AuthenticationManager,
     * and return a JWT upon successful authentication.
     * @param credentials A map containing the user's "username" and "password".
     * @return A ResponseEntity. For now, it returns a simple success message.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        // In a real application, you would validate the credentials against the database.
        String username = credentials.get("username");

        // For now, just return a success message to confirm the endpoint is reachable.
        return ResponseEntity.ok("Login endpoint reached for user: " + username);
    }
}
