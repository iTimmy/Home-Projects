package service;

import java.util.*;
import dao.*;
import dto.*;

public interface VendingMachineService {
    List<VendingMachine> getAllItems();

    int getItem(String itemName, int itemQuantity, double userMoney) throws Exception;

    void updateItem(String itemName, int numOfItems, int itemQuantity, String userItemName) throws Exception;

    void removeItem(String itemName, int itemQuantity);
}