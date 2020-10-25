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
    int newOrderNumber = 0;

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

    private Order calculation(Order order) {
        MathContext mc = new MathContext(3);
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes state = new StateTaxes();

        // CALCULATIONS \\
        BigDecimal laborCostPerSquareFoot = getProductByName(order.getProduct().getProductType())
                .getLaborCostPerSquareFoot().round(mc);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();

        String stateName = order.getTax().getState();
        Tax storedTax = getTaxByState(stateName);
        if (storedTax == null) {
            return null;
        }

        // get most recent order number of the collections. make method.

        BigDecimal customerStateTaxRate = roundBigDecimal(state.fetchStateTax(stateName));
        BigDecimal taxRate = storedTax.getTaxRate();

        BigDecimal taxPercentage = new BigDecimal(100);
        
        BigDecimal materialCost = roundBigDecimal(
                calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot));

        BigDecimal laborCost = roundBigDecimal(
                calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot));
        BigDecimal tax = calculate.calculate(MathOperator.MULTIPLY,
                calculate.calculate(MathOperator.PLUS, materialCost, laborCost),
                calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage)).setScale(2, RoundingMode.FLOOR);

        BigDecimal totalCost = roundBigDecimal(calculate.calculate(MathOperator.PLUS,
                calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax));

        order.getProduct().setCostPerSquareFoot(costPerSquareFoot);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.getTax().setTaxRate(taxRate);

        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setOrderTax(tax);
        order.setTotalCost(totalCost);

        return order;
    }

    // ORDERS \\
    @Override
    public Order createOrder(Order order) throws Exception {
        order.setOrderNumber(generateOrderNumber());
        order = calculation(order);
        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> getActiveOrders() throws Exception {
        List<Order> createdOrders = orderDao.getActiveOrders();
        if (createdOrders == null) {
            return null;
        }
        return createdOrders;
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
    public Order getOrderByID(int orderNumber) {
        Order order = orderDao.getOrderByID(orderNumber);
        return order;
    }

    @Override
    public void updateOrder(Order editedOrder, Order existingOrder) {
        try {
            calculation(editedOrder);
            orderDao.updateOrder(editedOrder, existingOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(Order orderNumber) throws Exception {
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
        // double generateOrderNumber = Math.random() * 100;
        // int newOrderNumber = (int)generateOrderNumber;
        // grab the greatest order number from the dao
        newOrderNumber++;
        return newOrderNumber;
    }
}
