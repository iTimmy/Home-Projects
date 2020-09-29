/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.flooringmastery.service.*;
import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.*;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author Music Account
 */
public class FlooringMasteryServiceImplTest {
    
    FlooringMasteryService serviceTest;

    public FlooringMasteryServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        serviceTest = ctx.getBean("service", FlooringMasteryService.class);
    }

    FlooringMasteryOrderDao orderDaoTest = new FlooringMasteryOrderDaoImpl();
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();
    
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
    public void testCreateOrderCalculation() throws Exception {
        MathContext mc = new MathContext(4);
        BigDecimalMath calculate = new BigDecimalMath();
        
        LocalDate date = LocalDate.of(2025, Month.DECEMBER, 3);
        
        Order order = new Order();
        order.setOrderDate(date);
        order.setCustomerName("Timmy");
        order.setArea(new BigDecimal(233).round(mc));
       
        Product product = new Product();
        product.setProductType("Tile");
        product.setCostPerSquareFoot(serviceTest.getProductByName(product.getProductType()).getCostPerSquareFoot());
        
        Tax tax = new Tax();
        tax.setState("TX");
    
        order.setProduct(product);
        order.setTax(tax);
        
//        System.out.println(order.getOrderDate());
//        System.out.println(order.getCustomerName());
//        System.out.println(order.getArea());
//        System.out.println(order.getOrderNumber());
//        System.out.println(serviceTest.getProductByName("Tile"));
//        System.out.println("gjhgj");
//        System.out.println(order.getTax().getState());
//        System.out.println(serviceTest.getTaxByState("TX"));

        Order calculatedOrder = serviceTest.createOrder(order);
        assertEquals(calculatedOrder.getTotalCost(), new BigDecimal(1861.77).setScale(2, RoundingMode.CEILING));
    }  
}
