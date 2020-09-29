package com.sg.flooringmastery.dao;

import java.io.*;
import java.math.*;
import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    @Autowired
    FlooringMasteryStorage storage = new FlooringMasteryStorage();
    Map<Integer, Order> storeOrders = new HashMap<>();
    Map<Integer, Order> loadOrders = new HashMap<>();
    private File fileOrders = new File("");
    private final static String DELIMITER = ",";

    // public FlooringMasteryOrderDaoImpl(File textFile) {
    // this.fileOrders = textFile;
    // }

    private File importFile(LocalDate orderDate) throws Exception {
        fileOrders = storage.doesFileExist(orderDate);
        if (fileOrders == null) {
            return null;
        } else {
            return fileOrders;
        }
    }

    @Override
    public Order createOrder(Order order) throws Exception {
        // this step is skipped if the file exists
        if (storage.doesFileExist(order.getOrderDate()) == null) {
            storage.createNewFile(order.getOrderDate());
        }
        importFile(order.getOrderDate());
        storeOrders.put(order.getOrderNumber(), order);
        // System.out.println("storeOrders: " + storeOrders);
        return order;
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        List<Order> listOrders = new ArrayList<>(storeOrders.values());
        return listOrders;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        loadOrders.clear();

        if (importFile(userInputDate) == null) {
            return null;
        }

        if (loadOrders.isEmpty() == true) {
            loadDataToDisplay();
        }
        List<Order> listOrders = new ArrayList<>(loadOrders.values());
        return listOrders;
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        try {
            if (orderNumber != storeOrders.get(orderNumber).getOrderNumber()) {
                return null;
            } else {
                // deleteOrder(storeOrders.get(orderNumber));
                return storeOrders.get(orderNumber);
            }
        } catch (Exception e) {

        }
        return storeOrders.get(orderNumber);
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        Order editOrder = getOrderByID(order.getOrderNumber());

        // System.out.println(order.getOrderDate());
        // System.out.println(order.getCustomerName());
        // System.out.println(order.getArea());
        // System.out.println(order.getOrderNumber());
        try {
            editOrder.setCustomerName(order.getCustomerName());
            editOrder.setArea(order.getArea());
            editOrder.setMaterialCost(order.getMaterialCost());
            editOrder.setLaborCost(order.getLaborCost());
            editOrder.setOrderTax(order.getOrderTax());
            editOrder.setTotalCost(order.getTotalCost());
            editOrder.getProduct().setProductType(order.getProduct().getProductType());
            editOrder.getProduct().setCostPerSquareFoot(order.getProduct().getCostPerSquareFoot());
            editOrder.getProduct().setLaborCostPerSquareFoot(order.getProduct().getLaborCostPerSquareFoot());
            editOrder.getTax().setState(order.getTax().getState());
            editOrder.getTax().setTaxRate(order.getTax().getTaxRate());
            storeOrders.put(editOrder.getOrderNumber(), editOrder);
            saveData();
        } catch (Exception e) {
 
        }
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        loadData();
        if (storeOrders.remove(order.getOrderNumber()) != null) {
            saveData();
        } else {
            System.out.println("Order does not exist.");
        }
    }

    @Override
    public boolean saveOrdersByDate() throws Exception {
        if (storeOrders.isEmpty() == true) {
            return false;
        } else {
            loadData();
            saveData();
            storeOrders.clear();
            return true;
        }
    }







    private void saveData() throws Exception {
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(fileOrders, false)));
        // System.out.println("Saving: " + fileOrders);
        Collection<Order> storeOrdersValues = storeOrders.values();
        for (Order orderDetail : storeOrdersValues) {
            String currentOrders = marshallData(orderDetail);
            // System.out.println("currentOrders: " + currentOrders);
            writeFile.println(currentOrders);
            writeFile.flush();
        }
        writeFile.close();
    }
    private String marshallData(Order orderDetail) {
        Product product = orderDetail.getProduct();
        Tax tax = orderDetail.getTax();

        String ordersToFile = String.valueOf(orderDetail.getOrderNumber()) + DELIMITER + orderDetail.getCustomerName()
                + DELIMITER + tax.getState() + DELIMITER + String.valueOf(tax.getTaxRate()) + DELIMITER
                + product.getProductType() + DELIMITER + String.valueOf(orderDetail.getArea()) + DELIMITER
                + String.valueOf(product.getCostPerSquareFoot()) + DELIMITER
                + String.valueOf(product.getLaborCostPerSquareFoot()) + DELIMITER
                + String.valueOf(orderDetail.getMaterialCost()) + DELIMITER + String.valueOf(orderDetail.getLaborCost())
                + DELIMITER + String.valueOf(orderDetail.getOrderTax()) + DELIMITER
                + String.valueOf(orderDetail.getTotalCost());

        return ordersToFile;
    }

    private void loadData() throws Exception {
        try {
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileOrders)));
            // System.out.println("Loading: " + fileOrders);
            String currentLine = "";
            if (readFile.hasNextLine()) {
                while (readFile.hasNextLine()) {
                    currentLine = readFile.nextLine();
                    Order ordersFromFile = unmarshallData(currentLine);
                    storeOrders.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                }
            } else {
                System.out.println("The file is empty.");
                storage.deleteFileIfEmpty(fileOrders);
            }
            readFile.close();
        } catch (Exception e) {}
    }
    
    private void loadDataToDisplay() throws Exception {
        try {
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileOrders)));
            // System.out.println("Loading: " + fileOrders);
            String currentLine = "";
            if (readFile.hasNextLine()) {
                while (readFile.hasNextLine()) {
                    currentLine = readFile.nextLine();
                    Order ordersFromFile = unmarshallData(currentLine);
                    loadOrders.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                }
            } else {
                System.out.println("The file is empty.");
                storage.deleteFileIfEmpty(fileOrders);
            }
            readFile.close();
        } catch (Exception e) {
        }
    }
    private Order unmarshallData(String currentLine) {
        String[] line = currentLine.split(DELIMITER);

        // ORDERS \\
        String orderNumberString = line[0]; int orderNumber = Integer.parseInt(orderNumberString);
        String customerName = line[1];
        String stateName = line[2];
        String taxRateString = line[3]; BigDecimal taxRate = new BigDecimal(taxRateString);
        String productType = line[4];
        String areaString = line[5]; BigDecimal area = new BigDecimal(areaString);
        String costPerSquareFootString = line[6]; BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootString);
        String laborCostPerSquareFootString = line[7]; BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFootString);
        String materialCostString = line[8]; BigDecimal materialCost = new BigDecimal(materialCostString); 
        String laborCostString = line[9]; BigDecimal laborCost = new BigDecimal(laborCostString);
        String taxString = line[10]; BigDecimal tax = new BigDecimal(taxString);
        String totalCostString = line[11]; BigDecimal totalCost = new BigDecimal(totalCostString);

        Product productsFromFile = new Product();
        productsFromFile.setProductType(productType);
        productsFromFile.setCostPerSquareFoot(costPerSquareFoot);
        productsFromFile.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        Tax taxesFromFile = new Tax();
        taxesFromFile.setState(stateName);
        taxesFromFile.setTaxRate(taxRate);

        Order ordersFromFile = new Order(orderNumber);
        ordersFromFile.setOrderNumber(orderNumber);
        ordersFromFile.setCustomerName(customerName);
        ordersFromFile.setArea(area);
        ordersFromFile.setMaterialCost(materialCost);
        ordersFromFile.setLaborCost(laborCost);
        ordersFromFile.setOrderTax(tax);
        ordersFromFile.setTotalCost(totalCost);
        ordersFromFile.setArea(area);

        ordersFromFile.setProduct(productsFromFile);
        ordersFromFile.setTax(taxesFromFile);

        return ordersFromFile;
    }
}