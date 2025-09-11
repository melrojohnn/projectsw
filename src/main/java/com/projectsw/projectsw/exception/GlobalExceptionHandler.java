package com.projectsw.projectsw.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class catches specified exceptions and formats a consistent JSON error response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException, typically thrown for validation errors.
     * @return A ResponseEntity with a 400 Bad Request status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Bad Request");
    }

    /**
     * Handles EntityNotFoundException, thrown when a resource is not found.
     * @return A ResponseEntity with a 404 Not Found status.
     */
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(jakarta.persistence.EntityNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND, "Not Found");
    }

    /**
     * Handles DataIntegrityViolationException, typically for unique constraint violations.
     * @return A ResponseEntity with a 409 Conflict status.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        String message = "Data integrity error. This may be due to a duplicate entry (e.g., email already exists).";
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT, "Conflict", message);
    }

    /**
     * A catch-all handler for any other unexpected exceptions.
     * @return A ResponseEntity with a 500 Internal Server Error status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Log the full stack trace for debugging on the server side
        ex.printStackTrace(); 
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    /**
     * Helper method to build a consistent error response body.
     */
    private ResponseEntity<Object> buildErrorResponse(Exception ex, WebRequest request, HttpStatus status, String error) {
        return buildErrorResponse(ex, request, status, error, ex.getMessage());
    }
    
    private ResponseEntity<Object> buildErrorResponse(Exception ex, WebRequest request, HttpStatus status, String error, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
