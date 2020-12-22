package com.example.backend.service;

public class DVDDaoDuplicateIdException extends Exception {

    public DVDDaoDuplicateIdException(String message) {
        super(message);
    }

    public DVDDaoDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }

}