package com.example.storageapp.domain.exceptions;

/**
 * Error handler required for validation if the resource does not exist
 */

public class ResourceExistException extends Exception {
    public ResourceExistException(String message) {
        super(message);
    }
}
