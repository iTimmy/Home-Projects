package service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;
import dto.*;
import dao.*;

public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();
    FlooringMasteryProductDao productDao;
    FlooringMasteryTaxDao taxDao;

    /*
    public FlooringMasteryServiceImpl(FlooringMasteryOrderDao order, FlooringMasteryProductDao product, FlooringMasteryTaxDao tax) {
        this.order = order;
        this.product = product;
        this.tax = tax;
    } */
 


    // ORDERS \\
    @Override
    public Order createOrder(Order order) {
        System.out.println("Running service layer...");
        MathContext mc = new MathContext(3);
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes state = new StateTaxes();

        // CALCULATIONS \\
        BigDecimal laborCostPerSquareFoot = new BigDecimal(2.12).round(mc);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();

        String stateName = order.getTax().getState();
        BigDecimal taxRate = state.fetchStateTax(stateName);


        BigDecimal taxPercentage = new BigDecimal(100).round(mc);
 
        BigDecimal materialCost = calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot).round(mc);
        BigDecimal laborCost = calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot).round(mc);
        BigDecimal tax = calculate.calculate(MathOperator.MULTIPLY, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage)).round(mc);
        BigDecimal totalCost = calculate.calculate(MathOperator.PLUS, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax).round(mc);

        order.getProduct().setCostPerSquareFoot(costPerSquareFoot);
        order.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.getTax().setTaxRate(taxRate);
        order.setOrderNumber(generateOrderNumber());
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setOrderTax(tax);
        order.setTotalCost(totalCost);

        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> getAllOrders(boolean loadOrSave) throws Exception {
        List<Order> listOrders = orderDao.getAllOrders(loadOrSave);
        if (listOrders == null) {
            return null;
        }
        return listOrders;
    }

    @Override
    public Order getOrderByID(LocalDate userInputDate) {
        if (orderDao.getOrderByID(userInputDate) != null) {
            return orderDao.getOrderByID(userInputDate);
        }
        return null;
    }

    @Override
    public void updateOrder(Order orderNumber) {

    }

    @Override
    public void deleteOrder(Order orderNumber) {

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
    public Tax getTaxByFloat(BigDecimal tax) {
        return ((FlooringMasteryService) tax).getTaxByFloat(tax);
    }

    private int generateOrderNumber() throws NumberFormatException {
        double generateOrderNumber = Math.random() * 100;
        int newOrderNumber = (int)generateOrderNumber;
        return newOrderNumber;
    }
}
