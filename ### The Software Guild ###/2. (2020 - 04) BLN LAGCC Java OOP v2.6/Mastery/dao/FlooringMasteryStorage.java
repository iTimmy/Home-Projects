package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dto.Order;
import dto.Product;
import dto.Tax;

public class FlooringMasteryStorage {
    File fileOrders = new File("Orders/Orders_08212017.txt");
    File fileTaxes = new File("Taxes.txt");
    File fileProducts = new File("Products.txt");

    private final static String DELIMITER = ",";

    Map<LocalDate, Order> storeOrders = new HashMap<>();
    // Map<LocalDate, Map<Integer, Order>> storeAllDataByDate = new HashMap<>();

    public void dataAccess(boolean loadOrSave) throws Exception {
        if (loadOrSave == false) {
            loadData();
        } else if (loadOrSave == true) {
            saveData();
        }
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
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(fileOrders)));
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
