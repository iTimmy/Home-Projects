package com.mycompany.classroster.dao;

import java.util.*;

public class ClassRosterPersistenceException extends Exception{
    
    public ClassRosterPersistenceException(String message) {
        super(message);
    }
    
    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}