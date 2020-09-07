package dao;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto.Order;
import dto.Product;
import dto.Tax;

public class FlooringMasteryStorage {
    private LocalDate currentDate = LocalDate.now();
    File fileOrders = new File("");
    File fileTaxes = new File("Taxes.txt");
    File fileProducts = new File("Products.txt");
    Formatter newFile;
    Formatter overwriteFile;

    private final static String DELIMITER = ",";

    Map<LocalDate, Order> storeOrders = new HashMap<>();
    // Map<LocalDate, Map<Integer, Order>> storeAllDataByDate = new HashMap<>();

    public boolean dataAccess(boolean loadOrSave) throws Exception {
        if (loadOrSave == false) {
            loadData();
        } else if (loadOrSave == true) {
            if (!storeOrders.isEmpty()) {
                doesFileExist();
                newFile = new Formatter("Orders/Orders_" + formatDate(currentDate.toString()) + ".txt");
                saveData();
            } else if (storeOrders.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String formatDate(String currentDate) {
        String[] tokens = currentDate.split("-");
        String year = tokens[0];
        String month = tokens[1];
        String day = tokens[2];
        String newFormat = month + day + year;
        return newFormat;
    }

    private File doesFileExist() throws Exception {
        // INIT
        String formattedDate = "Orders/Orders_" + "" + ".txt";
        String fileName = "";

        // STREAM FILES IN DIRECTORY
        Path findExistingFile = Paths.get("Orders/");
            Stream<Path> subPath = Files.walk(findExistingFile);
            List<String> orderFiles = subPath.filter(Files::isRegularFile)
                .map(Objects::toString)
                .collect(Collectors.toList());
            subPath.close();
            System.out.println(orderFiles);

        List<LocalDate> storeDates = new ArrayList<>();
        for (LocalDate currentDate : storeOrders.keySet()) {
            storeDates.add(currentDate);
        }

        // FORMAT USER INPUT DATE TO FILE NAME CONVENTION
        for (LocalDate currentDate : storeDates) {
            formattedDate = "Orders/Orders_" + formatDate(storeOrders.get(currentDate).getOrderDate().toString()) + ".txt";
        }

        // CHECK IF FILENAME CLASHES WITH ANOTHER IN THE 'ORDERS' DIRECTORY
        for (String currentFile : orderFiles) {
            if (formattedDate.equals(currentFile)) {
                overwriteFile = new Formatter("Orders/Orders_" + currentFile + ".txt");
                fileName = "Orders/Orders_" + currentFile + ".txt";
            } else if (!formattedDate.equals(currentFile)) {
                newFile = new Formatter("Orders/Orders_" + formatDate(currentDate.toString()) + ".txt");
                fileName = "Orders/Orders_" + formatDate(currentDate.toString()) + ".txt";
            }
        }        

        fileOrders = new File(fileName);

        return fileOrders;
    }

    public Map<LocalDate, Order> storeOrders() {
        return storeOrders;
    }

    /*
    public Map<LocalDate, Map<Integer, Order>> storeAllDataByDate() {
        return storeAllDataByDate;
    }
    */



    private void saveData() throws Exception {
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(fileOrders, true)));
        Collection<Order> storeOrdersValues = storeOrders.values();
        for (Order orderDetail : storeOrdersValues) {
            String currentOrders = marshallData(orderDetail);
            writeFile.println(currentOrders);
            writeFile.flush();
        }
        writeFile.close();
    }
    private String marshallData(Order orderDetail) {
        Product product = orderDetail.getProduct();
        Tax tax = orderDetail.getTax();

        String ordersToFile = 
        String.valueOf(orderDetail.getOrderNumber()) + 
        DELIMITER + 
        orderDetail.getCustomerName() +
        DELIMITER +
        tax.getState() +
        DELIMITER +
        String.valueOf(tax.getTaxRate()) +
        DELIMITER +
        product.getProductType() +
        DELIMITER +
        String.valueOf(orderDetail.getArea()) + 
        DELIMITER + 
        String.valueOf(product.getCostPerSquareFoot()) +
        DELIMITER +
        String.valueOf(product.getLaborCostPerSquareFoot()) +
        DELIMITER +
        String.valueOf(orderDetail.getMaterialCost()) + 
        DELIMITER + 
        String.valueOf(orderDetail.getLaborCost()) +
        DELIMITER + 
        String.valueOf(orderDetail.getOrderTax()) + 
        DELIMITER + 
        String.valueOf(orderDetail.getTotalCost());
 
        return ordersToFile;
    }



    private void loadData() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileOrders)));
        String currentLine = "";
        while(readFile.hasNextLine()) {
            currentLine = readFile.nextLine();
            Order ordersFromFile = unmarshallData(currentLine);
            storeOrders.put(ordersFromFile.getOrderDate(), ordersFromFile);
            // storeAllDataByDate.put(ordersFromFile.getOrderDate(), storeOrders);
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
