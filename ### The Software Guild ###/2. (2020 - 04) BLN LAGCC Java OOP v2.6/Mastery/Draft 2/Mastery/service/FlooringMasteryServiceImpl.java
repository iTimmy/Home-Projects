package service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import dto.*;
import dao.*;

public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();
    FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoImpl();
    FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoImpl();

    /*
    public FlooringMasteryServiceImpl(FlooringMasteryOrderDao order, FlooringMasteryProductDao product, FlooringMasteryTaxDao tax) {
        this.order = order;
        this.product = product;
        this.tax = tax;
    } */
 
    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        BigDecimal roundedBigDecimal = decimal.setScale(2, RoundingMode.CEILING);
        return roundedBigDecimal;
    }

    private void searching() {
        System.out.println("[2] Searching...");
    }

    // ORDERS \\
    @Override
    public Order createOrder(Order order) {
        MathContext mc = new MathContext(3);
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes state = new StateTaxes();

        // CALCULATIONS \\
        BigDecimal laborCostPerSquareFoot = new BigDecimal(2.12).round(mc);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();

        String stateName = order.getTax().getState();
        Tax storedTax = getTaxByState(stateName);
            if (storedTax == null) {
                return null;
            }

        BigDecimal customerStateTaxRate = roundBigDecimal(state.fetchStateTax(stateName));
        BigDecimal taxRate = roundBigDecimal(storedTax.getTaxRate());

        BigDecimal taxPercentage = new BigDecimal(100).round(mc);
 
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
    public boolean saveAllOrders(LocalDate userInputOrderDate) throws Exception {
        if (orderDao.saveAllOrders(userInputOrderDate) == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders(LocalDate userInputOrderDate) throws Exception {
        List<Order> listOrders = orderDao.getAllOrders(userInputOrderDate);
        if (listOrders == null) {
            return null;
        }
        return listOrders;
    }

    @Override
    public boolean getOrderByDate(LocalDate userInputDate) throws Exception {
        if (orderDao.getOrderByDate(userInputDate) != true) {
            System.out.println("does not exist");
            return false;
        } else {
            System.out.print("exist" + userInputDate);
            return true;
        }
    }

    @Override
    public void updateOrder(Order orderNumber) {
        createOrder(orderNumber);
    }

    @Override
    public void deleteOrder(Order orderNumber) {
        searching();
        orderDao.deleteOrder(orderNumber);
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
