/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.service.*;
import com.sg.vendingmachine.dto.VendingMachine;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.sg.vendingmachine.dto.UserWallet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Music Account
 */
public class VendingMachineServiceImplTest {
    
    VendingMachineService serviceTest;
    
    public VendingMachineServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        serviceTest = ctx.getBean("service", VendingMachineService.class);
    }
    
    VendingMachineDao daoTest = new VendingMachineDaoImpl();
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
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
