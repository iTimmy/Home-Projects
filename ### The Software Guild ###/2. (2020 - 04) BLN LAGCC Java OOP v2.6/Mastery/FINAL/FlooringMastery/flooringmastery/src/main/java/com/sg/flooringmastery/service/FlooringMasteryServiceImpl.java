package com.sg.flooringmastery.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import com.sg.flooringmastery.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryOrderDao orderDao;
    FlooringMasteryProductDao productDao;
    FlooringMasteryTaxDao taxDao;

    @Autowired
    public FlooringMasteryServiceImpl(FlooringMasteryOrderDao orderDao, FlooringMasteryProductDao productDao, FlooringMasteryTaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        BigDecimal roundedBigDecimal = decimal.setScale(2, RoundingMode.CEILING);
        return roundedBigDecimal;
    }

    private void searching() {
        System.out.println("[2] Searching...");
    }

    private Order calculation(Order order) {
        MathContext mc = new MathContext(3);
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes state = new StateTaxes();

        // CALCULATIONS \\
        // System.out.println("tilee" +
        // getProductByName("Tile").getLaborCostPerSquareFoot());
        BigDecimal laborCostPerSquareFoot = getProductByName(order.getProduct().getProductType())
                .getLaborCostPerSquareFoot().round(mc);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();

        String stateName = order.getTax().getState();
        Tax storedTax = getTaxByState(stateName);
        if (storedTax == null) {
            return null;
        }

        BigDecimal customerStateTaxRate = roundBigDecimal(state.fetchStateTax(stateName));
        BigDecimal taxRate = storedTax.getTaxRate();
        // System.out.println(storedTax.getTaxRate());
        // System.out.println(taxRate);

        BigDecimal taxPercentage = new BigDecimal(100);
        // System.out.println(order.getArea());
        // System.out.println(order.getProduct().getCostPerSquareFoot());
        BigDecimal materialCost = roundBigDecimal(
                calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot));
        // System.out.println("costPerSquareFoot: " + costPerSquareFoot);
        // System.out.println("Material Cost: " + materialCost);
        BigDecimal laborCost = roundBigDecimal(
                calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot));
        System.out.println("Labor Cost: " + laborCost);
        BigDecimal tax = calculate.calculate(MathOperator.MULTIPLY,
                calculate.calculate(MathOperator.PLUS, materialCost, laborCost),
                calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage));
        // System.out.println(taxPercentage);
        // System.out.println(calculate.calculate(MathOperator.PLUS, materialCost, laborCost));
        // System.out.println(calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage));
        // System.out.println("Tax: " + tax);
        BigDecimal totalCost = roundBigDecimal(calculate.calculate(MathOperator.PLUS,
                calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax));
        // System.out.println("Total Cost: " + totalCost);

        order.getProduct().setCostPerSquareFoot(costPerSquareFoot);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.getTax().setTaxRate(taxRate);

        order.setOrderNumber(generateOrderNumber());
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setOrderTax(tax);
        order.setTotalCost(totalCost);

        return order;
    }

    // ORDERS \\
    @Override
    public Order createOrder(Order order) throws Exception {
        order = calculation(order);
        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        List<Order> listOrders = orderDao.getAllOrders();
        if (listOrders == null) {
            return null;
        }
        return listOrders;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        List<Order> listOrders = orderDao.getOrdersByDate(userInputDate);
        if (listOrders == null) {
            return null;
        } else {
            return listOrders;
        }
    }

    @Override
    public void updateOrder(Order orderNumber) {
        try {
            calculation(orderNumber);
            orderDao.updateOrder(orderNumber);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(Order orderNumber) throws Exception {
        searching();
        orderDao.deleteOrder(orderNumber);
    }   

    @Override
    public boolean saveOrdersByDate() throws Exception {
        if (orderDao.saveOrdersByDate() == false) {
            return false;
        } else {
            return true;
        }
    }



    // PRODUCTS \\
    @Override
    public List<Product> getAllProducts() {
        List<Product> listProducts = productDao.getAllProducts();
        return listProducts;
    }

    @Override
    public Product getProductByName(String productName) {
//        System.out.println("p : " + productDao.getProductByName(productName));
        return productDao.getProductByName(productName);
    }



    // TAXES \\
    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> listTaxes = taxDao.getAllTaxes();
        return listTaxes;
    }

    @Override
    public Tax getTaxByState(String state) {
        return taxDao.getTaxByState(state);
    }

    private int generateOrderNumber() throws NumberFormatException {
        double generateOrderNumber = Math.random() * 100;
        int newOrderNumber = (int)generateOrderNumber;
        return newOrderNumber;
    }
}
