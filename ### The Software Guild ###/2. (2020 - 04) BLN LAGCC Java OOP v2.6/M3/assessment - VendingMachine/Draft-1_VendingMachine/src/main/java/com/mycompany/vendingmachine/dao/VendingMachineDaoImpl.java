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
    public VendingMachine getItem(String userInputItemName, int userInputItemQuantity, BigDecimal userInputMoney) throws Exception {
        
        if (allItems.isEmpty() == true) { 
            System.out.println("me: " + userInputItemQuantity);
            loadItems();
            System.out.println("me: " + userInputItemQuantity);
        } else if (allItems.isEmpty() == false) {
            if (!allItems.get(userInputItemName.toUpperCase()).getItemName().equals(userInputItemName)) {
                return null;
            } else {
                saveItems(userInputItemName.toUpperCase(), userInputItemQuantity);
                return allItems.get(userInputItemName.toUpperCase());
            }
        }

        System.out.println("me: " + userInputItemQuantity);
        System.out.println("not" + allItems.get(userInputItemName.toUpperCase()).getItemQuantity());
        if (userInputItemQuantity > allItems.get(userInputItemName.toUpperCase()).getItemQuantity()) {
            System.out.println("exiting.......");
            return null;
        }
        
        return allItems.get(userInputItemName.toUpperCase());
    }

    @Override
    public VendingMachine getItem(String userInputItemName) {
        return allItems.get(userInputItemName);
    }

    @Override
    public BigDecimal updateWallet(BigDecimal itemCost) {
        //allItems.get()
        return itemCost;
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        System.out.println("Deleting...");
        if (itemQuantity <= 0) {
            System.out.println(itemQuantity + " <= 0");
            allItems.remove(itemName);
            try {
                saveItems(itemName, itemQuantity);
                loadItems();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Deleted...");
    }

    private void saveItems(String userInputItemName, int userInputItemQuantity) throws Exception {
        PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        Collection<VendingMachine> allItemValues = allItems.values();
        for (VendingMachine vm : allItemValues) {
            String currentItems = marshallItems(vm, userInputItemName, userInputItemQuantity);
            fileWrite.println(currentItems);
            fileWrite.flush();
        }
        fileWrite.close();
    }
    private String marshallItems(VendingMachine itemsToFile, String userInputItemName, int userInputItemQuantity) {
        String itemNameToString = itemsToFile.getItemName();
        BigDecimal itemCostToDouble = itemsToFile.getItemCost();
        String itemCost = itemCostToDouble.toString();
        int itemNumToInt = itemsToFile.getItemQuantity();
        if (userInputItemName.equals(itemsToFile.getItemName())) {
            itemNumToInt = itemsToFile.getItemQuantity() - userInputItemQuantity;

            if (allItems.get(userInputItemName).getItemName().equals(userInputItemName))
            removeItem(userInputItemName, allItems.get(userInputItemName).getItemQuantity());
            updateWallet(itemCostToDouble);

        }
        String allItemDetails = itemNameToString.toUpperCase() + DELIMITER + 
        itemCost + DELIMITER + 
        Integer.toString(itemNumToInt);

        return allItemDetails;
    }

    private VendingMachine unmarshallItems(String itemsToString) {;
        // CONVERTS WORDS FROM FILES TO VARIABLES
        System.out.println("Marshalling");
        String[] itemTokens = itemsToString.split(DELIMITER);
        String itemName = itemTokens[0].toUpperCase();
        String itemCostString = itemTokens[1];
        System.out.println("Marshalling");
        double itemCostDouble = Double.parseDouble(itemCostString);
        BigDecimal itemCost = new BigDecimal(itemCostDouble);
        String itemQuantityString = itemTokens[2];
        int itemQuantity = Integer.parseInt(itemQuantityString);

        removeItem(itemName, itemQuantity);

        VendingMachine itemsFromFile = new VendingMachine(itemName, itemCost);
        itemsFromFile.setItemQuantity(itemQuantity);

        return itemsFromFile;
    }
    private void loadItems() throws Exception {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
        System.out.println(file);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            VendingMachine currentItems = unmarshallItems(currentLine);
            allItems.put(currentItems.getItemName(), currentItems);
            System.out.println("finish loading");
        }
    }
}