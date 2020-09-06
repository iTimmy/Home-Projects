package com.mycompany.classroster.dao;

import com.mycompany.classroster.controller.*;
import com.mycompany.classroster.dao.*;
import com.mycompany.classroster.dto.*;
import com.mycompany.classroster.view.*;
import java.util.*;

public interface ClassRosterDao {
        Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException;
         //throws ClassRosterDaoException;
    
        List<Student> getAllStudents()
         throws ClassRosterPersistenceException;
    
        Student getStudent(String studentId)
         throws ClassRosterPersistenceException;

        Student removeStudent(String studentId)
         throws ClassRosterPersistenceException;

}