package com.example.storageapp.domain.exceptions;

/**
 * Error handler required for validation if the resource is not found
 */

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
