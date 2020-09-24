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
import com.mycompany.vendingmachine.dto.UserWallet;

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
        UserWallet userWalletTest = new UserWallet();
        userWalletTest.setMoney(new BigDecimal(1.25));
        
        String userInputItemName = "soda";
        
        VendingMachine itemTest = new VendingMachine();
        itemTest.setItemName(userInputItemName.toUpperCase());
        itemTest.setItemCost(new BigDecimal(1.25));
        itemTest.setItemQuantity(10);
        
        BigDecimal expResult = mathCalculate.calculate(MathOperator.MINUS, userWalletTest.getMoney(), itemTest.getItemCost());
        
        VendingMachineServiceImpl instance = new VendingMachineServiceImpl();
        
        BigDecimal result = instance.moneyCalculation(MathOperator.MINUS, userWalletTest, itemTest);
        
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(result, result);
    }
    
}
