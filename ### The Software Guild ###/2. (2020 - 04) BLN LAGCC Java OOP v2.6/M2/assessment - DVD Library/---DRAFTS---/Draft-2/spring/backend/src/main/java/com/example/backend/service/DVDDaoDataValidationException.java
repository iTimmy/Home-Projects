package com.example.backend.service;

public class DVDDaoDataValidationException extends Exception {
    
    public DVDDaoDataValidationException(String message) {
        super(message);
    }

    public DVDDaoDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}