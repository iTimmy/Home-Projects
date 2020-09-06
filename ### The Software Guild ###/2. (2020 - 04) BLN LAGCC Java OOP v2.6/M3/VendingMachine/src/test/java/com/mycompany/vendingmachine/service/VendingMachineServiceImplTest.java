/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachine;
import com.mycompany.vendingmachine.service.*;
import java.math.BigDecimal;
import java.util.List;
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
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
    
    public VendingMachineServiceImplTest() {
        
    }
 /*  
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
*/
    /**
     * Test of getAllItems method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testGetAllItems() {
        System.out.println("getAllItems");
        VendingMachineServiceImpl instance = null;
        List<VendingMachine> expResult = null;
        List<VendingMachine> result = instance.getAllItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testGetItem() throws Exception {
        List<VendingMachine> items = serviceTest.getAllItems();
        VendingMachine oneItem = new VendingMachine("pizza", 1.00);
        assertEquals(1, items.size());
        assertTrue(items.get(0).equals(oneItem));
    }

    /**
     * Test of removeItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        String itemName = "";
        int itemQuantity = 0;
        VendingMachineServiceImpl instance = null;
        instance.removeItem(itemName, itemQuantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moneyCalculation method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testMoneyCalculation() {
        System.out.println("moneyCalculation");
        MathOperator operator = null;
        double userInputMoney = 0.0;
        String userInputItemName = "";
        VendingMachineServiceImpl instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.moneyCalculation(operator, userInputMoney, userInputItemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
