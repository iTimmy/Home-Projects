/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Music Account
 */
public class FlooringMasteryTaxDaoImplTest {
    FlooringMasteryTaxDao taxDaoTest;
    
    public FlooringMasteryTaxDaoImplTest() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
         taxDaoTest = ctx.getBean("taxDao", FlooringMasteryTaxDao.class);
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
    
    
    
    @Test
    public void testGetAllTaxes() {
        List<Tax> listTaxes = taxDaoTest.getAllTaxes();
        System.out.println(listTaxes.size());
        assertEquals(5, listTaxes.size());
    }
    
    @Test
    public void testGetTaxByState() {
        List<Tax> listTaxes = taxDaoTest.getAllTaxes();
        Tax taxOne = listTaxes.get(0);
        Tax taxDaoOne = taxDaoTest.getTaxByState(taxOne.getState());
        assertEquals(taxOne, taxDaoOne);
    }
}
