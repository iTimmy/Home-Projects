package com.mycompany.vendingmachine.dao;

import java.util.*;
import java.io.*;
import com.mycompany.vendingmachine.dto.*;
import java.math.BigDecimal;

public class VendingMachineDaoImpl implements VendingMachineDao {
    Map<String, VendingMachine> allItems = new HashMap<>();
    private final String DELIMITER = " :: ";
    private String file;

    public VendingMachineDaoImpl() {
        this.file = "VendingMachine.txt";
    }
    public VendingMachineDaoImpl(String textFile) {
        this.file = textFile;
    }

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> listItems = new ArrayList<VendingMachine>(allItems.values());
        try {
            loadItems();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listItems;
    }

    @Override
    public VendingMachine getItem(String userInputItemName) throws Exception {
        if (allItems.isEmpty()) {
            loadItems();
        } 
        
        if (!userInputItemName.equals(allItems.get(userInputItemName).getItemName())) {
            return null;
        } else {
            return allItems.get(userInputItemName.toUpperCase());
        }
    }
    
    @Override
    public void updateItems(VendingMachine item) throws Exception {
        saveItems();
        loadItems();
    }

    @Override
    public BigDecimal updateWallet(BigDecimal itemCost) {
        //allItems.get()
        return itemCost;
    }

    @Override
    public void removeItem(VendingMachine item) {
        System.out.println("Deleting...");
        System.out.println(allItems);
        allItems.remove(item.getItemName());
        try {
            System.out.println(allItems);
            updateItems(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Deleted...");
    }

    private void saveItems() throws Exception {
        PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        Collection<VendingMachine> allItemValues = allItems.values();
        for (VendingMachine vm : allItemValues) {
            String currentItems = marshallItems(vm);
            fileWrite.println(currentItems);
            fileWrite.flush();
        }
        fileWrite.close();
    }
    private String marshallItems(VendingMachine itemsToFile) {
        String itemNameToString = itemsToFile.getItemName();
        BigDecimal itemCostToDouble = itemsToFile.getItemCost();
        String itemCost = itemCostToDouble.toString();
        int itemNumToInt = itemsToFile.getItemQuantity();

        String allItemDetails = itemNameToString.toUpperCase() + DELIMITER + 
        itemCost + DELIMITER + 
        Integer.toString(itemNumToInt);

        return allItemDetails;
    }

    private VendingMachine unmarshallItems(String itemsToString) {
        // CONVERTS WORDS FROM FILES TO VARIABLES;
        String[] itemTokens = itemsToString.split(DELIMITER);
        String itemName = itemTokens[0].toUpperCase();
        String itemCostString = itemTokens[1];
        double itemCostDouble = Double.parseDouble(itemCostString);
        BigDecimal itemCost = new BigDecimal(itemCostDouble);
        String itemQuantityString = itemTokens[2];
        int itemQuantity = Integer.parseInt(itemQuantityString);

        VendingMachine itemsFromFile = new VendingMachine(itemName, itemCost);
        itemsFromFile.setItemQuantity(itemQuantity);

        return itemsFromFile;
    }
    private void loadItems() throws Exception {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
        // System.out.println(file);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            VendingMachine currentItems = unmarshallItems(currentLine);
            allItems.put(currentItems.getItemName(), currentItems);
        }
    }
}