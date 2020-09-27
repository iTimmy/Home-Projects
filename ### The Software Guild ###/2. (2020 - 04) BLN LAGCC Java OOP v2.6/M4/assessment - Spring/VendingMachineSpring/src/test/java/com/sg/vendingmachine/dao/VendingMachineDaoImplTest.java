/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.dto.VendingMachine;
import com.sg.vendingmachine.service.MathOperator;
import java.util.List;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
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
public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
    VendingMachineService service;
    MathContext mc = new MathContext(2);
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        String testFile = "VendingMachineTest.txt";
        // Use the FileWriter to quickly blank the file
        // new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<VendingMachine> list = testDao.getAllItems();
        VendingMachine itemOne = new VendingMachine();
        itemOne.setItemName("pringles");
        itemOne.setItemCost(new BigDecimal(2.5));
        itemOne.setItemQuantity(50);
        list.add(itemOne);
        
        VendingMachine itemTwo = new VendingMachine();
        itemTwo.setItemName("soda");
        itemTwo.setItemCost(new BigDecimal(2.5));
        itemTwo.setItemQuantity(50);
        list.add(itemTwo);

        assertEquals(2, list.size());
    }

    /**
     * Test of getItem method, of class VendingMachineDaoImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItem_String() throws Exception {
        String itemNameTest = "pringles";
        BigDecimal itemCostTest = new BigDecimal(2.5).round(mc);
        VendingMachine item = new VendingMachine();
        
        item.setItemName(itemNameTest.toUpperCase());
        item.setItemCost(itemCostTest);
        item.setItemQuantity(6);

        VendingMachine result = testDao.getItem(item.getItemName()); // 4
//        System.out.println("getItem 2: " + item.getItemQuantity()); 
//        System.out.println(item.getItemName().equals("pringles"));
//        System.out.println(itemNameTest);
//        System.out.println(result.getItemName());
        assertEquals(item.getItemName(), result.getItemName());
        assertTrue(item.getItemName().equals(result.getItemName()));
    }
}
