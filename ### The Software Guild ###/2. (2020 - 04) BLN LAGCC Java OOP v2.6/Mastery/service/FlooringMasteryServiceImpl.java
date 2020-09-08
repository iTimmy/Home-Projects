package service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;
import dto.*;
import dao.*;

public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();
    FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoImpl();
    FlooringMasteryTaxDao taxDao;

    /*
    public FlooringMasteryServiceImpl(FlooringMasteryOrderDao order, FlooringMasteryProductDao product, FlooringMasteryTaxDao tax) {
        this.order = order;
        this.product = product;
        this.tax = tax;
    } */
 
    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        int countPlaces = String.valueOf(decimal).length() + 2;

        MathContext mc = new MathContext(countPlaces);
        BigDecimal roundedBigDecimal = decimal.round(mc);
        
        return roundedBigDecimal;
    }

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
        BigDecimal taxRate = roundBigDecimal(state.fetchStateTax(stateName));
        System.out.println(taxRate);


        BigDecimal taxPercentage = new BigDecimal(100).round(mc);
        System.out.println(taxPercentage);
 
        BigDecimal materialCost = roundBigDecimal(calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot));
        BigDecimal laborCost = roundBigDecimal(calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot));
        BigDecimal tax = roundBigDecimal(calculate.calculate(MathOperator.MULTIPLY, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage)));
        BigDecimal totalCost = roundBigDecimal(calculate.calculate(MathOperator.PLUS, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax));

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
    public Order getOrderByDate(LocalDate userInputDate) {
        try {
            if (userInputDate == null) {
                return null;
            }
        } catch (Exception e) {
            System.out.println("No");
        } 
        
        if (orderDao.getOrderByDate(userInputDate) != null) {
            return orderDao.getOrderByDate(userInputDate);
        }
        return null;
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        throw new UnsupportedOperationException("hold");
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
    public Tax getTaxByState(String state) {
        return taxDao.getTaxByState(state);
    }

    private int generateOrderNumber() throws NumberFormatException {
        double generateOrderNumber = Math.random() * 100;
        int newOrderNumber = (int)generateOrderNumber;
        return newOrderNumber;
    }
}
