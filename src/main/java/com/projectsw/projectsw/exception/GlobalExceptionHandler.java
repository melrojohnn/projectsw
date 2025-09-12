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
 * This class uses @ControllerAdvice to intercept exceptions thrown by any controller
 * and formats a consistent, user-friendly JSON error response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException, typically thrown for business logic validation errors (e.g., invalid rank for a faction).
     * @param ex The caught IllegalArgumentException.
     * @param request The current web request.
     * @return A ResponseEntity with a 400 Bad Request status and a detailed error message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Bad Request");
    }

    /**
     * Handles EntityNotFoundException, thrown when a resource (e.g., a character or mission) is not found in the database.
     * @param ex The caught EntityNotFoundException.
     * @param request The current web request.
     * @return A ResponseEntity with a 404 Not Found status.
     */
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(jakarta.persistence.EntityNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND, "Not Found");
    }

    /**
     * Handles DataIntegrityViolationException, which occurs when a database constraint is violated (e.g., unique email constraint).
     * @param ex The caught DataIntegrityViolationException.
     * @param request The current web request.
     * @return A ResponseEntity with a 409 Conflict status to indicate the request cannot be processed due to a conflict with the current state of the resource.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        String message = "Data integrity error. This may be due to a duplicate entry (e.g., email already exists).";
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT, "Conflict", message);
    }

    /**
     * A generic, catch-all handler for any other unexpected exceptions.
     * This prevents the application from sending a default, unformatted error page.
     * It logs the stack trace for debugging purposes.
     * @param ex The caught generic Exception.
     * @param request The current web request.
     * @return A ResponseEntity with a 500 Internal Server Error status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Log the full stack trace for debugging on the server side
        ex.printStackTrace(); 
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    /**
     * Private helper method to build a consistent JSON error response body.
     * @param ex The exception.
     * @param request The web request.
     * @param status The HTTP status.
     * @param error The error description (e.g., "Not Found").
     * @return A ResponseEntity containing the structured error map.
     */
    private ResponseEntity<Object> buildErrorResponse(Exception ex, WebRequest request, HttpStatus status, String error) {
        return buildErrorResponse(ex, request, status, error, ex.getMessage());
    }
    
    /**
     * Overloaded helper method to allow for a custom message.
     */
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
