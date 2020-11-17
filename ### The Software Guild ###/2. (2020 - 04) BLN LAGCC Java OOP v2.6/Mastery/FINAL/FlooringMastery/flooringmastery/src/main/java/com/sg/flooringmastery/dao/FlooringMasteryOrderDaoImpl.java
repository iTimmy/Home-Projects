package com.sg.flooringmastery.dao;

import java.io.*;
import java.math.*;
import java.time.LocalDate;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    FlooringMasteryStorage storage = new FlooringMasteryStorage();
    // STORE TO SAVE
    Map<Integer, Order> storeOrders = new HashMap<>();
    // STORE TO DISPLAY
    Map<Integer, Order> loadOrders = new HashMap<>();
    // STORE TO COUNT CURRENT CREATED ORDERS
    Map<Integer, Order> storeAllOrdersFromFiles = new HashMap<>();
    List<Order> activeOrders = new ArrayList<>();
    private String file;
    private File fileOrders = new File("");
    private final static String DELIMITER = ",";
    private String dataExportFile;

    @Autowired
    public FlooringMasteryOrderDaoImpl() {
        this.dataExportFile = "Backup\\DataExport.txt";
        // this.file = file;
    }

    public FlooringMasteryOrderDaoImpl(String dataExportFile/*, String file*/) {
        this.dataExportFile = dataExportFile;
        // this.file = file;
    }

    private int generateOrderNumber() throws NumberFormatException, Exception {
        List<String> orderFiles = storage.importAllOrders();
        for (String s : orderFiles) {
            File file = new File(s);
            loadDataFromFiles(file);
        }
        //System.out.println(storeAllOrdersFromFiles);
        
        int max = 0;
        for (Order eachOrder : storeAllOrdersFromFiles.values()) {
            if (eachOrder.getOrderNumber() > max) {
                max = eachOrder.getOrderNumber();
            }

        }
        int newOrderNumber = max;
        newOrderNumber++;
        return newOrderNumber;
    }

    private File importFile(LocalDate orderDate) throws Exception {
        fileOrders = storage.loadFile(orderDate);
        if (fileOrders == null) {
            return null;
        } else {
            return fileOrders;
        }
    }

    @Override
    public Order createOrder(Order order) throws Exception {
        // this step is skipped if the file exists
        if (storage.loadFile(order.getOrderDate()) == null) {
            storage.createNewFile(order.getOrderDate());
        }
        storeOrders.clear();
        importFile(order.getOrderDate());
        loadData();
        order.setOrderNumber(generateOrderNumber());
        activeOrders.add(order);
        storeOrders.put(order.getOrderNumber(), order);
        saveDataToOrders();
        return order;
    }

    @Override
    public List<Order> getActiveOrders() throws Exception {
        return activeOrders;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        loadOrders.clear();

        if (importFile(userInputDate) == null) {
            return null;
        }

        if (loadOrders.isEmpty() == true) {
            loadDataToDisplay();
            loadData();
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
    public void updateOrder(Order editedOrder, Order existingOrder) throws Exception {
        try {
            existingOrder.setCustomerName(editedOrder.getCustomerName());
            existingOrder.setArea(editedOrder.getArea());
            existingOrder.setMaterialCost(editedOrder.getMaterialCost());
            existingOrder.setLaborCost(editedOrder.getLaborCost());
            existingOrder.setOrderTax(editedOrder.getOrderTax());
            existingOrder.setTotalCost(editedOrder.getTotalCost());
            existingOrder.getProduct().setProductType(editedOrder.getProduct().getProductType());
            existingOrder.getProduct().setCostPerSquareFoot(editedOrder.getProduct().getCostPerSquareFoot());
            existingOrder.getProduct().setLaborCostPerSquareFoot(editedOrder.getProduct().getLaborCostPerSquareFoot());
            existingOrder.getTax().setState(editedOrder.getTax().getState());
            existingOrder.getTax().setTaxRate(editedOrder.getTax().getTaxRate());
            activeOrders.add(editedOrder);
            storeOrders.put(editedOrder.getOrderNumber(), editedOrder);
            saveDataToOrders();
        } catch (Exception e) {
 
        }
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        loadData();
        if (storeOrders.remove(order.getOrderNumber()) != null) {
//            activeOrders.remove(order.getOrderNumber());
            saveDataToOrders();
        } else {
            System.out.println("Order does not exist.");
        }
    }

    @Override
    public boolean saveOrdersByDate() throws Exception {
        List<String> orderFiles = storage.importAllOrders();
        for (String currentFile : orderFiles) {
            File file = new File(currentFile);
            loadDataFromFiles(file);
            saveDataToExportData();
        }
        if (storeOrders.isEmpty() == true) {
            return false;
        } else {
            return true;
        }
    }





    private void saveDataToExportData() throws Exception {
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(dataExportFile)));
        for (Order orderDetail : storeAllOrdersFromFiles.values()) {
            String currentOrders = marshallDataExport(orderDetail);
            writeFile.println(currentOrders);
            writeFile.flush();
        }
        writeFile.close();
    }
    private String marshallDataExport(Order orderDetail) {
        Product product = orderDetail.getProduct();
        Tax tax = orderDetail.getTax();

        String ordersToFile = String.valueOf(orderDetail.getOrderDate())
                + DELIMITER + String.valueOf(orderDetail.getOrderNumber()) 
                + DELIMITER + orderDetail.getCustomerName()
                + DELIMITER + tax.getState() 
                + DELIMITER + String.valueOf(tax.getTaxRate()) 
                + DELIMITER + product.getProductType() 
                + DELIMITER + String.valueOf(orderDetail.getArea()) 
                + DELIMITER + String.valueOf(product.getCostPerSquareFoot()) 
                + DELIMITER + String.valueOf(product.getLaborCostPerSquareFoot()) 
                + DELIMITER + String.valueOf(orderDetail.getMaterialCost()) 
                + DELIMITER + String.valueOf(orderDetail.getLaborCost())
                + DELIMITER + String.valueOf(orderDetail.getOrderTax()) 
                + DELIMITER + String.valueOf(orderDetail.getTotalCost());

        return ordersToFile;
    }

    private void saveDataToOrders() throws Exception {
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
    
    private void loadDataFromFiles(File currentFile) throws Exception {
        try {
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(currentFile)));
            // System.out.println("Loading: " + fileOrders);
            String[] parts = currentFile.toString().split("_|\\.");
            String dateString = parts[1].substring(4,8) + "-" + parts[1].substring(0,2) + "-" + parts[1].substring(2,4);
            LocalDate date = storage.formatStringToDate(dateString);
            // System.out.println(date);
            String currentLine = "";
            if (readFile.hasNextLine()) {
                while (readFile.hasNextLine()) {
                    currentLine = readFile.nextLine();
                    Order ordersFromFile = unmarshallData(currentLine);
                    storeAllOrdersFromFiles.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                    ordersFromFile.setOrderDate(date);
                }
            } 
            readFile.close();
        } catch (Exception e) {}
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
        //String dateString = line[12]; LocalDate date = storage.formatStringToDate(dateString);

        Product productsFromFile = new Product();
        productsFromFile.setProductType(productType);
        productsFromFile.setCostPerSquareFoot(costPerSquareFoot);
        productsFromFile.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        Tax taxesFromFile = new Tax();
        taxesFromFile.setState(stateName);
        taxesFromFile.setTaxRate(taxRate);

        Order ordersFromFile = new Order(orderNumber);
        //ordersFromFile.setOrderDate(date);
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