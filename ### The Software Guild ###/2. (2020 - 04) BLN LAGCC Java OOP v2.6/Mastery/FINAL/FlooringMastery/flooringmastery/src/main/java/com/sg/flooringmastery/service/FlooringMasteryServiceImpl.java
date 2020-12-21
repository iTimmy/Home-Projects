package com.sg.flooringmastery.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import com.sg.flooringmastery.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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



    // ---------------- ORDERS ---------------- \\
    @Override
    public Order createOrder(Order order) throws Exception {
        return orderDao.createOrder(calculation(order));
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        return orderDao.getOrdersByDate(userInputDate);
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        return orderDao.getOrderByID(orderNumber);
    }

    @Override
    public void updateOrder(Order editedOrder, Order existingOrder) throws Exception {
        orderDao.updateOrder(calculation(editedOrder), existingOrder);
    }

    @Override
    public void deleteOrder(Order orderNumber) throws Exception {
        orderDao.deleteOrder(orderNumber);
    }   

    @Override
    public boolean saveOrdersByDate() throws Exception {
        return orderDao.saveOrdersByDate();
    }



    // ---------------- PRODUCTS ---------------- \\
    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProductByName(String productName) {
        return productDao.getProductByName(productName);
    }



    // ---------------- TAXES ---------------- \\
    @Override
    public List<Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public Tax getTaxByState(String state) {
        return taxDao.getTaxByState(state);
    }





    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.CEILING);
    }

    private Order calculation(Order order) {
        MathContext mc = new MathContext(3);
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes state = new StateTaxes();

        // CALCULATIONS \\
        BigDecimal laborCostPerSquareFoot = getProductByName(order.getProduct().getProductType()).getLaborCostPerSquareFoot().round(mc);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();
        String stateName = order.getTax().getState();
        Tax storedTax = getTaxByState(stateName);
        if (storedTax == null) return null;
        
        System.out.println("sdgdfgdgfdg");

        // get most recent order number of the collections. make method.
        BigDecimal taxRate = storedTax.getTaxRate();
        BigDecimal taxPercentage = new BigDecimal(100);
        BigDecimal materialCost = roundBigDecimal(calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot));
        BigDecimal laborCost = roundBigDecimal(calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot));
        BigDecimal tax = calculate.calculate(MathOperator.MULTIPLY, calculate.calculate(MathOperator.PLUS, materialCost, laborCost),
            calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage))
            .setScale(2, RoundingMode.FLOOR);
        BigDecimal totalCost = roundBigDecimal(calculate.calculate(MathOperator.PLUS, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax));

        order.getProduct().setCostPerSquareFoot(costPerSquareFoot);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.getTax().setTaxRate(taxRate);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setOrderTax(tax);
        order.setTotalCost(totalCost);

        return order;
    }

}
