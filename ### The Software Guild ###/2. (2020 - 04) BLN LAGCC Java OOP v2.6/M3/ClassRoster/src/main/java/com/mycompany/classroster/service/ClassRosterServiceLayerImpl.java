/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.*;
import com.mycompany.classroster.dto.Student;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Music Account
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    
    ClassRosterDao dao;
   
    public ClassRosterServiceLayerImpl(ClassRosterDao dao) {
        this.dao = dao;
    }

    ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      @Override
        public List<Student> getAllStudents() throws ClassRosterPersistenceException {
            return dao.getAllStudents();
        }

        @Override
        public Student getStudent(String studentId) throws ClassRosterPersistenceException {
            return dao.getStudent(studentId);
        }

        @Override
        public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
            return dao.removeStudent(studentId);
        }

        /*
        private void listStudents() throws ClassRosterPersistenceException {
            List<Student> studentList = service.getAllStudents();

            view.displayStudentList(studentList);
        }

        private void viewStudent() throws ClassRosterPersistenceException {
             String studentId = view.getStudentIdChoice();
             Student student = service.getStudent(studentId) ;
             view.displayStudent(student);
        }

        private void createStudent() throws ClassRosterPersistenceException {
            view.displayCreateStudentBanner();
            boolean hasErrors = false;
            do {
                Student currentStudent = view.getNewStudentInfo();
                try {
                    service.createStudent(currentStudent);
                    view.displayCreateSuccessBanner();
                    hasErrors = false;
                } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
            } while (hasErrors);
        }

        private void removeStudent() throws ClassRosterPersistenceException {
            view.displayRemoveStudentBanner();
            String studentId = view.getStudentIdChoice();
            service.removeStudent(studentId);
            view.displayRemoveSuccessBanner();
        }
*/
   }

  