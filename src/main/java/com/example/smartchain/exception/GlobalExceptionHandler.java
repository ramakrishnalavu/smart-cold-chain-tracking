package com.example.smartchain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonError(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        Map<String, String> response = new HashMap<>();
        response.put("message", "JSON Parsing Error: Check your field names and data types.");
        response.put("details", ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex) {
        ex.printStackTrace();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Access Denied: You do not have permission to perform this action.");
        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Internal Server Error: " + ex.getMessage());
        response.put("type", ex.getClass().getSimpleName());
        return ResponseEntity.status(500).body(response);
    }
}
