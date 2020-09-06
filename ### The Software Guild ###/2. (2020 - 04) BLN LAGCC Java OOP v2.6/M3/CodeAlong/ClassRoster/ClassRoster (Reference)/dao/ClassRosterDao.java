package dao;

import controller.*;
import dao.*;
import dto.*;
import ui.*;
import java.util.*;

public interface ClassRosterDao {
        Student addStudent(String studentId, Student student);
         //throws ClassRosterDaoException;
    
        List<Student> getAllStudents()
         throws ClassRosterDaoException;
    
        Student getStudent(String studentId)
         throws ClassRosterDaoException;

        Student removeStudent(String studentId)
         throws ClassRosterDaoException;

}