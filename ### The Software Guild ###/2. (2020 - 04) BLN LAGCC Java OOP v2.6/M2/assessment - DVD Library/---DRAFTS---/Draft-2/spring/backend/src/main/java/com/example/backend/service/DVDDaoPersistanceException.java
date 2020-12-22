package com.example.backend.service;

public class DVDDaoPersistanceException extends Exception {
    
    public DVDDaoPersistanceException(String message) {
        super(message);
    }

    public DVDDaoPersistanceException(String message, Throwable cause) {
        super(message, cause);
    }
}