/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.VendingMachine;
import com.mycompany.vendingmachine.service.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.vendingmachine.dao.*;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
    VendingMachineDao daoTest = new VendingMachineDaoImpl();
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
    
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
    public void testMoneyCalculation() {
        System.out.println("moneyCalculation");
        BigDecimal userInputMoney = new BigDecimal(2.5);
        String userInputItemName = "pringles";
        
        VendingMachine item = new VendingMachine();
        item.setItemName(userInputItemName);
        item.setItemCost(new BigDecimal(2.5));
        item.setItemQuantity(10);
        
        VendingMachineServiceImpl instance = new VendingMachineServiceImpl();
        BigDecimal expResult = new BigDecimal(0);
        
        BigDecimal result = instance.moneyCalculation(MathOperator.MINUS, new BigDecimal(2.5), item.getItemName());
        
        assertEquals(expResult, result);
    }
    
}
