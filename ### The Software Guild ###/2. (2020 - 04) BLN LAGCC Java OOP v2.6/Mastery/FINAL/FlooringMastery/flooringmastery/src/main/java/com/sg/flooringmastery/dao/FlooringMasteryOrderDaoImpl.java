package com.sg.flooringmastery.dao;

import java.io.*;
import java.math.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    private Map<LocalDate, Map<Integer, Order>> allOrders = new HashMap<>();
    private List<String> orderFiles = new ArrayList<>();
    private final static String DELIMITER = ",";
    private String dataExportFile;
    private String file;
    private String path;



    @Autowired
    public FlooringMasteryOrderDaoImpl() {
        this.dataExportFile = "Backup\\DataExport.txt";
        this.path = "Orders\\";
    }

    public FlooringMasteryOrderDaoImpl(String dataExportFile, String path) {
        this.dataExportFile = dataExportFile;
        this.path = path;
    }



    private int generateOrderNumber(Order order) throws NumberFormatException, Exception {
        int max = 0;
        for (Map<Integer, Order> eachMap : allOrders.values()) {
            for (int eachOrder : eachMap.keySet()) {
                if (eachOrder > max) max = eachOrder;
            }
        }
        int newOrderNumber = max;
        newOrderNumber++;
        return newOrderNumber;
    }

    private void createNewFile(LocalDate orderDate) throws IOException {
        File newFile = new File("Orders_" + formatDateToString(orderDate) + ".txt");
        newFile.createNewFile();
        orderFiles.add(newFile.toString());
    }

    private void deleteFileIfEmpty(File file) {
        file.delete();
    }

    private void importFiles() throws Exception {
        File f = new File(path);
        for (String pathname : f.list()) {
            if (!pathname.equals("null")) {
                loadAllOrdersFromFiles(pathname);
            }
        }
        
    }

    private boolean doesFileExist(LocalDate userInputOrderDate) throws Exception {
        importFiles();
        // FORMAT USER INPUT DATE
        String formattedDate = "Orders_" + formatDateToString(userInputOrderDate) + ".txt";
        String a = "";
        // CHECK IF FILENAME CLASHES WITH ANOTHER IN THE 'ORDERS' DIRECTORY
        for (LocalDate currentDate : allOrders.keySet()) {
            String currentFile = "Orders_" + formatDateToString(currentDate) + ".txt";
            if (formattedDate.equals(currentFile)) { // file found
                file = formattedDate;
                a = currentFile;
                break;
            } else if (!formattedDate.equals(currentFile)) { // file not found
                file = formattedDate;
                a = currentFile;
            }
        }
        System.out.println(formattedDate.equals(a));
        return formattedDate.equals(a);
    }

    private File loadFile(LocalDate userInputOrderDate) throws Exception {
        return (doesFileExist(userInputOrderDate) == true) ? new File(file) : null;
    }












    @Override
    public Order createOrder(Order order) throws Exception {
        if (loadFile(order.getOrderDate()) == null) {
            createNewFile(order.getOrderDate());
            allOrders.put(order.getOrderDate(), new HashMap<Integer, Order>());
        } else {
            loadOrdersFromFile();
        }
        file = formatDateToFile(order.getOrderDate());
        order.setOrderNumber(generateOrderNumber(order));
        Map<Integer, Order> newOrder = allOrders.get(order.getOrderDate());
        newOrder.put(order.getOrderNumber(), order);
        allOrders.put(order.getOrderDate(), newOrder);

        saveOrdersToFile(order.getOrderDate());
        return order;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        if (loadFile(userInputDate) != null) {
            loadOrdersFromFile();
            return new ArrayList<>(allOrders.get(userInputDate).values());
        } else {
            return null;
        }
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        for (Map<Integer, Order> eachMap : allOrders.values()) {
            for (Order eachOrder : eachMap.values()) {
                if (eachOrder.getOrderNumber() == orderNumber) {
                    return eachOrder;
                }
            }
        }
        return null;
    }

    @Override
    public void updateOrder(Order editedOrder, Order existingOrder) throws Exception {
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

        Map<Integer, Order> orders = new HashMap<>();
        orders.put(editedOrder.getOrderNumber(), editedOrder);
        allOrders.put(editedOrder.getOrderDate(), orders);
        saveOrdersToFile(editedOrder.getOrderDate());
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        allOrders.get(order.getOrderDate()).remove(order.getOrderNumber());
        saveOrdersToFile(order.getOrderDate());
    }

    @Override
    public boolean saveOrdersByDate() throws Exception {
        if (allOrders.isEmpty()) {
            importFiles();
            saveDataToExportData();
        } else {
            saveDataToExportData();
        }
        return allOrders.isEmpty() ? false : true;
    }





    // DATA EXPORT \\
    private void saveDataToExportData() throws Exception {
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(dataExportFile)));
         for (Map<Integer, Order> eachMap : allOrders.values()) {
             for (Order eachOrder : eachMap.values()) {
                 String currentOrder = marshallDataExport(eachOrder);
                 writeFile.println(currentOrder);
                 writeFile.flush();
             }
         }
        writeFile.close();
    }
    private String marshallDataExport(Order orderDetail) {
        Product product = orderDetail.getProduct();
        Tax tax = orderDetail.getTax();
        String ordersToFile = String.valueOf(orderDetail.getOrderNumber()) 
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
                + DELIMITER + String.valueOf(orderDetail.getTotalCost()
                + DELIMITER + String.valueOf(orderDetail.getOrderDate()));
        return ordersToFile;
    }

    // STANDARD \\
    private void saveOrdersToFile(LocalDate date) throws Exception {
        PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(path + file, false)));
        for (Order orderDetail : allOrders.get(date).values()) {
            String currentOrders = marshallData(orderDetail);
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
    
    private void loadAllOrdersFromFiles(String currentFile) throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(path + currentFile)));
        if (readFile.hasNextLine()) {
            while (readFile.hasNextLine()) {
                String currentLine = readFile.nextLine();
                Order ordersFromFile = unmarshallData(currentLine);
                Map<Integer, Order> orders = new HashMap<>();
                orders.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                ordersFromFile.setOrderDate(formatFileToDate(currentFile));
                allOrders.put(ordersFromFile.getOrderDate(), orders);
            }
        } 
        readFile.close();
    }

    private void loadOrdersFromFile() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(path + file)));
        Map<Integer, Order> orders = new HashMap<>();
        if (readFile.hasNextLine()) {
            while (readFile.hasNextLine()) {
                String currentLine = readFile.nextLine();
                Order ordersFromFile = unmarshallData(currentLine);
                orders.put(ordersFromFile.getOrderNumber(), ordersFromFile);
                ordersFromFile.setOrderDate(formatFileToDate(file));
            }
        } else {
            // deleteFileIfEmpty(fileOrders);
        }
        allOrders.put(formatFileToDate(file), orders);
        readFile.close();
    }
    
    private LocalDate formatFileToDate(String filename) {
        String[] parts = filename.split("_|\\.");
        String dateString = parts[1].substring(4,8) + "-" + parts[1].substring(0,2) + "-" + parts[1].substring(2,4);
        return formatStringToDate(dateString);
    }
    
    private String formatDateToFile(LocalDate date) {
        String[] parts = String.valueOf(date).split("-");
        String fileString = "Orders_" + parts[1] + parts[2] + parts[0] + ".txt";
        return fileString;
    }
    
    private Order unmarshallData(String currentLine) {
        String[] line = currentLine.split(DELIMITER);
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



























     private String formatDateToString(LocalDate currentDate) {
        String currentDateString = currentDate.toString();
        String[] tokens = currentDateString.split("-");
        String year = tokens[0];
        String month = tokens[1];
        String day = tokens[2];
        String newFormat = month + day + year;
        return newFormat;
    }

    public LocalDate formatStringToDate(String date) {
        LocalDate orderOfDate = LocalDate.now();
        int i = 0;

        String yearString = "";
        String dayString = "";

        String dateSplit[] = date.split("-");
        for (String s : dateSplit) {
            if (i == 0) {
                yearString = s;
            } else if (i == 2) {
                dayString = s;
            }
            i++;
        }
         String dateMonth = dateSplit[1];
        int year = Integer.parseInt(yearString);
        int day = Integer.parseInt(dayString);

        orderOfDate = LocalDate.of(year, monthFormat(dateMonth), day);
        return orderOfDate;
    }

    public Month monthFormat(String dateMonth) {
        Month month = Month.JANUARY;
        if (dateMonth.equals("01")) {
            month = Month.JANUARY;
        } else if (dateMonth.equals("02")) {
            month = Month.FEBRUARY;
        } else if (dateMonth.equals("03")) {
            month = Month.MARCH;
        } else if (dateMonth.equals("04")) {
            month = Month.APRIL;
        } else if (dateMonth.equals("05")) {
            month = Month.MAY;
        } else if (dateMonth.equals("06")) {
            month = Month.JUNE;
        } else if (dateMonth.equals("07")) {
            month = Month.JULY;
        } else if (dateMonth.equals("08")) {
            month = Month.AUGUST;
        } else if (dateMonth.equals("09")) {
            month = Month.SEPTEMBER;
        } else if (dateMonth.equals("10")) {
            month = Month.OCTOBER;
        } else if (dateMonth.equals("11")) {
            month = Month.NOVEMBER;
        } else if (dateMonth.equals("12")) {
            month = Month.DECEMBER;
        } else {
            return null;
        }
        return month;
    }
}