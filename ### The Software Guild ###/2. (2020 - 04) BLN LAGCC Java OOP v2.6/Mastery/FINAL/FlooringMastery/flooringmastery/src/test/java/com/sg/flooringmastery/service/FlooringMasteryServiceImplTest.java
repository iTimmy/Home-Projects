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
import java.util.List;

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
    MathContext mc = new MathContext(4);
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
     public void test_Create_Update_Get_Calculation_Order() throws Exception {
         List<Product> listProducts = serviceTest.getAllProducts();
         List<Tax> listTaxes = serviceTest.getAllTaxes();
         
         Order order = serviceTest.getOrderByID(1);
         
         Order editedOrder = new Order(order.getOrderNumber());
         editedOrder.setOrderDate(order.getOrderDate());
         editedOrder.setCustomerName("Timmy");
         editedOrder.setArea(new BigDecimal(200).round(mc));
         editedOrder.setProduct(listProducts.get(0));
         editedOrder.setTax(listTaxes.get(0));
        
         serviceTest.updateOrder(editedOrder, order);
         
         List<Order> listOrders = serviceTest.getOrdersByDate(order.getOrderDate());
         assertEquals(editedOrder.getCustomerName(), listOrders.get(0).getCustomerName());
         assertEquals(editedOrder.getCustomerName(), serviceTest.getOrderByID(1).getCustomerName());
     }
    
     @Test
     public void testRemoveOrder() throws Exception {
         Order order = serviceTest.getOrderByID(1);
         serviceTest.deleteOrder(order);
         assertEquals("", order.getCustomerName());
     }

    @Test
    public void testCreateOrderCalculation() throws Exception {  
        LocalDate date = LocalDate.of(2025, Month.DECEMBER, 3);
        
        Order order = new Order();
        order.setOrderDate(date);
        order.setCustomerName("Ada");
        order.setArea(new BigDecimal(233).round(mc));
       
        Product product = new Product();
        product.setProductType("Vinyl");
        product.setCostPerSquareFoot(serviceTest.getProductByName(product.getProductType()).getCostPerSquareFoot());
        product.setLaborCostPerSquareFoot(serviceTest.getProductByName(product.getProductType()).getLaborCostPerSquareFoot());
       
        Tax tax = new Tax();
        tax.setState("TX");
    
        order.setProduct(product);
        order.setTax(tax);
        
        System.out.println(order.getOrderDate());
        System.out.println(order.getCustomerName());
        System.out.println(order.getArea());
        System.out.println(order.getOrderNumber());
        System.out.println(order.getProduct().getProductType());
        System.out.println(order.getTax().getState());

        Order calculatedOrder = serviceTest.createOrder(order);
        System.out.println(calculatedOrder.getOrderDate());
        System.out.println(calculatedOrder.getCustomerName());
        System.out.println(calculatedOrder.getArea());
        System.out.println(calculatedOrder.getOrderNumber());
        System.out.println(calculatedOrder.getProduct().getProductType());
        System.out.println(calculatedOrder.getTax().getState());
        System.out.println(serviceTest.getOrderByID(calculatedOrder.getOrderNumber()));
        
//        assertNotNull(serviceTest.getOrderByID(calculatedOrder.getOrderNumber()));
        assertNotNull(calculatedOrder);
        assertEquals(calculatedOrder.getCustomerName(), order.getCustomerName());
        assertEquals(calculatedOrder.getTotalCost(), order.getTotalCost());
    }
}  