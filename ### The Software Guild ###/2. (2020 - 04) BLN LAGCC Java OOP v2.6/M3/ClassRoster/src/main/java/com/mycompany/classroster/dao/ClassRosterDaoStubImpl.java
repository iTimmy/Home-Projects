/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Music Account
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {

    public Student onlyStudent;

    public ClassRosterDaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Ada");
        onlyStudent.setLastName("Lovelace");
        onlyStudent.setCohort("Java-May-1845");
    }

    public ClassRosterDaoStubImpl(Student testStudent){
         this.onlyStudent = testStudent;
     }

    /**
     *
     * @param studentId
     * @param student
     * @return
     * @throws ClassRosterPersistenceException
     */
    @Override
    public Student addStudent(String studentId, Student student)
                  throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents()
                 throws ClassRosterPersistenceException {
        List<Student> studentList = new ArrayList<>();
        studentList.add(onlyStudent);
        return studentList;
    }

    @Override
    public Student getStudent(String studentId)
                throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }       
    }

    @Override
    public Student removeStudent(String studentId)
                throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }   
}