/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterDao;
import com.mycompany.classroster.dao.ClassRosterDaoStubImpl;
import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Music Account
 */
public class ClassRosterServiceLayerImplTest {
    
    private ClassRosterServiceLayer service;
   
    public ClassRosterServiceLayerImplTest() {
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();

        service = new ClassRosterServiceLayerImpl(dao, auditDao);
    
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testCreateDuplicateIdStudent() {
        // ARRANGE
        Student student = new Student("0001");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        // ACT
        try {
            service.createStudent(student);
            fail("Expected DupeId Exception was not thrown.");
        } catch (ClassRosterDataValidationException
                | ClassRosterPersistenceException e) {
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (ClassRosterDuplicateIdException e){
            return;
        }
    }
    
    @Test
    public void testCreateStudentInvalidData() throws Exception {
        // ARRANGE
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        // ACT
        try {
            service.createStudent(student);
            fail("Expected ValidationException was not thrown.");
        } catch (ClassRosterDuplicateIdException
                | ClassRosterPersistenceException e) {
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (ClassRosterDataValidationException e){
            return;
        }  
    }
    
    @Test
    public void testGetAllStudents() throws Exception, ClassRosterPersistenceException {
        // ARRANGE
        Student testClone = new Student("0001");
            testClone.setFirstName("Ada");
            testClone.setLastName("Lovelace");
            testClone.setCohort("Java-May-1845");

        // ACT & ASSERT
        assertEquals( 1, service.getAllStudents().size(), 
                                       "Should only have one student.");
        assertTrue( service.getAllStudents().contains(testClone),
                                  "The one student should be Ada.");
    }
    
    @Test
    public void testGetStudent() throws Exception, ClassRosterPersistenceException {
        // ARRANGE
        Student testClone = new Student("0001");
            testClone.setFirstName("Ada");
            testClone.setLastName("Lovelace");
            testClone.setCohort("Java-May-1845");

        // ACT & ASSERT
        Student shouldBeAda = service.getStudent("0001");
        assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
        assertEquals( testClone, shouldBeAda,
                                       "Student stored under 0001 should be Ada.");

        Student shouldBeNull = service.getStudent("0042");    
        assertNull( shouldBeNull, "Getting 0042 should be null.");

    }
    
    @Test
    public void testRemoveStudent() throws Exception, ClassRosterPersistenceException {
        // ARRANGE
        Student testClone = new Student("0001");
            testClone.setFirstName("Ada");
            testClone.setLastName("Lovelace");
            testClone.setCohort("Java-May-1845");

        // ACT & ASSERT
        Student shouldBeAda = service.removeStudent("0001");
        assertNotNull( shouldBeAda, "Removing 0001 should be not null.");
        assertEquals( testClone, shouldBeAda, "Student removed from 0001 should be Ada.");

        Student shouldBeNull = service.removeStudent("0042");    
        assertNull( shouldBeNull, "Removing 0042 should be null.");

    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCreateValidStudent() {
        // ARRANGE
        Student student = new Student("0002");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");
        // ACT
        try {
            service.createStudent(student);
        } catch (ClassRosterDuplicateIdException
                | ClassRosterDataValidationException
                | ClassRosterPersistenceException e) {
        // ASSERT
            fail("Student was valid. No exception should have been thrown.");
    }
        
    
}
    
}
