package dao;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dto.Order;
import dto.Product;
import dto.Tax;

public class FlooringMasteryStorage {
    private List<String> orderFiles = new ArrayList<>();
    private File fileOrders = new File("");
    int i = 1;
    private final static String DELIMITER = ",";
    Map<Integer, Order> storeOrders = new HashMap<>();
    boolean trueOrFalse = false;

    public boolean dataAccess(boolean loadOrSave) throws Exception {
        if (loadOrSave == false) {
            //refreshData(true);
            System.out.println("DSfdsfsfsfds");
            loadData();
        } else if (loadOrSave == true) {
            saveData();
        }
        //System.out.println("There are " + orderFiles.size() + " existing orders.");
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

    public LocalDate findOrderByDate(LocalDate userInputOrderDate) throws Exception { 
        System.out.println("findOre" + userInputOrderDate);
        if (doesFileExist(userInputOrderDate) == null) {
            System.out.println("returning null storage");
            return null;
        }
        System.out.println("returning true storage");
        System.out.println(orderFiles);
        return userInputOrderDate;
    }

    private void importFiles() throws Exception {
        // ADD FILES FROM DIRECTORY TO LIST
        System.out.println("importFiles()");
        System.out.println(orderFiles);

        String[] pathnames;
        File f = new File("C:/Users/Music Account/Documents/Mastery/Orders");
        pathnames = f.list();

        for (String pathname : pathnames) {
            System.out.println(pathname);
            String name = "Orders\\" + pathname;
            orderFiles.add(name);
        }
        System.out.println(orderFiles);
    }

    public boolean refreshData(boolean trueOrFalse) {
        if (trueOrFalse == true) {
            if (storeOrders.isEmpty() == false) {
                storeOrders.clear();
            } else {
                System.out.println("storeOrders is empty.");
            }
        } else if (trueOrFalse == false) {

        }
        return trueOrFalse;
    }

    private File doesFileExist(LocalDate userInputOrderDate) throws Exception {
        // INIT
        refreshData(trueOrFalse);

        fileOrders = new File("");
        String formattedDate = "";

        System.out.println("Importing files...");
        importFiles();
        System.out.println(orderFiles);

        // FORMAT USER INPUT DATE
        formattedDate = "Orders\\Orders_" + formatDate(userInputOrderDate.toString()) + ".txt";

        String a = "";
        // CHECK IF FILENAME CLASHES WITH ANOTHER IN THE 'ORDERS' DIRECTORY
        for (String currentFile : orderFiles) {
            // OVERWRITE AN EXISTING FILE
            System.out.println(currentFile);
            if (formattedDate.equals(currentFile)) {
                fileOrders = new File(formattedDate);
                System.out.println("File has been found.");
                a = currentFile;
            // CREATE A NEW FILE
            } else if (!formattedDate.equals(currentFile)) {
                System.out.println("File has not been found.");
                fileOrders = new File(formattedDate);
                a = currentFile;
            }
        }        

        if (!formattedDate.equals(a)) {
            return null;
        }

        System.out.println("f: " + fileOrders);

        return fileOrders;
    }

    public Map<Integer, Order> storeOrders() {
        return storeOrders;
    }

    private void saveData() throws Exception {
        System.out.println("saving");
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(fileOrders, false)));
        Collection<Order> storeOrdersValues = storeOrders.values();
        // System.out.println("Saving " + fileOrders + "...");
        for (Order orderDetail : storeOrdersValues) {
            String currentOrders = marshallData(orderDetail);
            writeFile.println(currentOrders);
            writeFile.flush();
        }
        writeFile.close();
    }
    private String marshallData(Order orderDetail) {
        // System.out.println("[" + i + "] Writing...");
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
        System.out.println("g");
        try {
            System.out.println("gfdsfs" + fileOrders);
            Scanner readFile = new Scanner(new BufferedReader(new FileReader("Orders\\Orders_09132020.txt")));
            System.out.println("g");
            String currentLine = "";
            System.out.println("Loading " + fileOrders + "...");
            if (readFile.hasNextLine()) {
                while(readFile.hasNextLine()) {
                    currentLine = readFile.nextLine();
                    Order ordersFromFile = unmarshallData(currentLine);
                    System.out.println(ordersFromFile);
                    storeOrders.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                    System.out.println(storeOrders);
                }
            } else {
                System.out.println("The file is empty.");
                //deleteFile();
            }
            readFile.close();
        } catch (Exception e) {}
    }
    private Order unmarshallData(String currentLine) {
        // System.out.println("[" + i + "] Reading...");
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

        i++;

        return ordersFromFile;
    }

    // public void deleteFile() throws Exception {
    //     Scanner readFile = new Scanner(new BufferedReader(new FileReader("Orders\\test.txt")));
    //     System.out.println("running edeltingd");
    //     if (fileOrders.exists()) {
    //         System.out.println("running edeltingd");
    //         if (readFile.hasNextLine() != true) {
    //             System.out.println("running edeltingd");
    //             fileOrders.delete();
    //         }
    //     }
    // }
}
