package dao;

import java.util.*;
import java.io.*;
import dto.*;

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
    public VendingMachine getItem(String userInputItemName, int userInputItemQuantity, double userInputMoney) throws Exception {
        if (userInputItemQuantity > allItems.get(userInputItemName).getItemQuantity()) {
            System.out.println("exiting.......");
            return null;
        }
        if (allItems.isEmpty() == true) { 
            loadItems();
        } else if (allItems.isEmpty() == false) {
            if (!allItems.get(userInputItemName).getItemName().equals(userInputItemName)) {
                return null;
            } else {
                saveItems(userInputItemName, userInputItemQuantity);
                return allItems.get(userInputItemName);
            }
        }
        return allItems.get(userInputItemName);
    }

    public VendingMachine getItem(String userInputItemName) {
        return allItems.get(userInputItemName);
    }

    @Override
    public void updateItem(String itemName, int ItemQuantity, int itemQuantity, String userItemName) throws Exception {
        /*
        System.out.println(userItemName + itemName + ItemQuantity + itemQuantity);
        allItems.get(userItemName);
        if (itemName.equals(userItemName)) {
            ItemQuantity -= itemQuantity;
        } else {

        }
        try {
            saveItems();
        } catch (Exception e) {
        }
        */
    }

    @Override
    public double updateWallet(double itemCost) {
        return itemCost;
    }

    @Override
    public void removeItem(String itemName, int itemQuantity) {
        if (itemQuantity <= 0) {
            allItems.remove(itemName);
            try {
                saveItems(itemName, itemQuantity);
                loadItems();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
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
        double itemCostToDouble = itemsToFile.getItemCost();
        int itemNumToInt = itemsToFile.getItemQuantity();
        if (userInputItemName.equals(itemsToFile.getItemName())) {
            itemNumToInt = itemsToFile.getItemQuantity() - userInputItemQuantity;

            if (allItems.get(userInputItemName).getItemName().equals(userInputItemName))
            removeItem(userInputItemName, allItems.get(userInputItemName).getItemQuantity());
            updateWallet(itemCostToDouble);

        }
        String allItemDetails = itemNameToString.toUpperCase() + DELIMITER + 
        Double.toString(itemCostToDouble) + DELIMITER + 
        Integer.toString(itemNumToInt);

        return allItemDetails;
    }

    private VendingMachine unmarshallItems(String itemsToString) {;
        // CONVERTS WORDS FROM FILES TO VARIABLES
        String[] itemTokens = itemsToString.split(DELIMITER);
        String itemName = itemTokens[0];
        String itemCostString = itemTokens[1];
        double itemCost = Double.parseDouble(itemCostString);
        String itemQuantityString = itemTokens[2];
        int itemQuantity = Integer.parseInt(itemQuantityString);

        removeItem(itemName, itemQuantity);

        VendingMachine itemsFromFile = new VendingMachine(itemName, itemCost);
        itemsFromFile.setItemQuantity(itemQuantity);

        return itemsFromFile;
    }
    private void loadItems() throws Exception {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            VendingMachine currentItems = unmarshallItems(currentLine);
            allItems.put(currentItems.getItemName(), currentItems);
        }
    }
}