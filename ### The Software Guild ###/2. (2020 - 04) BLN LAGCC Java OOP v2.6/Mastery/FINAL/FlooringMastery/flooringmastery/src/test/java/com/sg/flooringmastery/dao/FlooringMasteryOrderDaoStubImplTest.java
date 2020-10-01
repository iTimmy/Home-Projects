/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Music Account
 */
public class FlooringMasteryOrderDaoStubImplTest {
    
    public FlooringMasteryOrderDaoStubImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    @Test
//    public void testCreateValidStudent() {
//        // ARRANGE
//        Student student = new Student("0002");
//        student.setFirstName("Charles");
//        student.setLastName("Babbage");
//        student.setCohort(".NET-May-1845");
//        // ACT
//        try {
//            service.createStudent(student);
//        } catch (ClassRosterDuplicateIdException
//                | ClassRosterDataValidationException
//                | ClassRosterPersistenceException e) {
//        // ASSERT
//            fail("Student was valid. No exception should have been thrown.");
//        }
//    }
//    
//    @Test
//    public void testCreateDuplicateIdStudent() {
//        // ARRANGE
//        Order order = new Order();
//        order.setCustomerName("Charles");
//        order.setOrderNumber(10);
//
//        // ACT
//        try {
//            orderDaoTest.createStudent(order);
//            fail("Expected DupeId Exception was not thrown.");
//        } catch (ClassRosterDataValidationException
//                | ClassRosterPersistenceException e) {
//        // ASSERT
//            fail("Incorrect exception was thrown.");
//        } catch (ClassRosterDuplicateIdException e){
//            return;
//        }
//    }
//    
//    @Test
//    public void testCreateStudentInvalidData() throws Exception {
//        // ARRANGE
//        Order order = new Order();
//        order.setCustomerName("Charles");
//        order.setOrderNumber(10);
//
//        // ACT
//        try {
//            service.createStudent(student);
//            fail("Expected ValidationException was not thrown.");
//        } catch (ClassRosterDuplicateIdException
//                | ClassRosterPersistenceException e) {
//        // ASSERT
//            fail("Incorrect exception was thrown.");
//        } catch (ClassRosterDataValidationException e){
//            return;
//        }  
//    }
//    
//    @Test
//    public void testGetAllStudents() throws Exception {
//        // ARRANGE
//        Order testClone = new Order();
//            testClone.setCustomerName("Ada");
//            testClone.setOrderNumber(54);
//
//        // ACT & ASSERT
//        assertEquals( 1, orderDaoTest.getAllStudents().size(), 
//                                       "Should only have one student.");
//        assertTrue( orderDaoTest.getAllStudents().contains(testClone),
//                                  "The one student should be Ada.");
//    }
//    
//    @Test
//    public void testGetStudent() throws Exception {
//        // ARRANGE
//        Order order = new Order();
//        order.setCustomerName("Charles");
//        order.setOrderNumber(10);
//
//        // ACT & ASSERT
//        Order shouldBeAda = orderDaoTest.getStudent("0001");
//        assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
//        assertEquals( testClone, shouldBeAda,
//                                       "Student stored under 0001 should be Ada.");
//
//        Order shouldBeNull = orderDaoTest.getStudent("0042");    
//        assertNull( shouldBeNull, "Getting 0042 should be null.");
//
//    }
//    
//    @Test
//    public void testRemoveStudent() throws Exception {
//        // ARRANGE
//        Order order = new Order();
//        order.setCustomerName("Charles");
//        order.setOrderNumber(10);
//
//        // ACT & ASSERT
//        Order shouldBeAda = orderDaoTest.removeStudent("0001");
//        assertNotNull( shouldBeAda, "Removing 0001 should be not null.");
//        assertEquals( testClone, shouldBeAda, "Student removed from 0001 should be Ada.");
//
//        Order shouldBeNull = orderDaoTest.removeStudent("0042");    
//        assertNull( shouldBeNull, "Removing 0042 should be null.");
//
//    }
//    
}
