package service;

import java.math.BigDecimal;
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
        BigDecimalMath calculate = new BigDecimalMath();
        StateTaxes st = new StateTaxes();

        // CALCULATIONS \\
        BigDecimal laborCostPerSquareFoot = new BigDecimal(2.12);
        BigDecimal costPerSquareFoot = order.getProduct().getCostPerSquareFoot();
        BigDecimal taxRate = st.stateSelect(States.AL, "NY");
        BigDecimal taxPercentage = new BigDecimal(100);
        BigDecimal area = order.getArea();

        BigDecimal materialCost = calculate.calculate(MathOperator.MULTIPLY, order.getArea(), costPerSquareFoot);
        BigDecimal laborCost = calculate.calculate(MathOperator.MULTIPLY, order.getArea(), laborCostPerSquareFoot);
        BigDecimal tax = calculate.calculate(MathOperator.MULTIPLY, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), calculate.calculate(MathOperator.DIVIDE, taxRate, taxPercentage));
        BigDecimal totalCost = calculate.calculate(MathOperator.PLUS, calculate.calculate(MathOperator.PLUS, materialCost, laborCost), tax);
        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> getAllOrders(boolean loadOrSave) throws Exception {
        List<Order> listOrders = orderDao.getAllOrders(loadOrSave);
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
}
