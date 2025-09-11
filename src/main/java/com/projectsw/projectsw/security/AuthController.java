package com.projectsw.projectsw.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // For now, this is a placeholder. We will implement the actual JWT generation later.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        // In a real application, you would validate the credentials against the database.
        String username = credentials.get("username");
        String password = credentials.get("password");

        // For now, just return a success message.
        return ResponseEntity.ok("Login endpoint reached for user: " + username);
    }
}
