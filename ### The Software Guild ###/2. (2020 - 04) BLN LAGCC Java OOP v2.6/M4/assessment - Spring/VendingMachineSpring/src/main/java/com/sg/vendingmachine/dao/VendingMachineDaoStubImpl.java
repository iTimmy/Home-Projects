/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendingMachine;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.sg.vendingmachine.dao.*;

/**
 *
 * @author Music Account
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    public VendingMachine onlyItem;

    public VendingMachineDaoStubImpl() {
        onlyItem = new VendingMachine();
        onlyItem.setItemName("CANDY");
        onlyItem.setItemQuantity(5);
        onlyItem.setItemCost(new BigDecimal(2.50));
    }

    public VendingMachineDaoStubImpl(VendingMachine testItem){
         this.onlyItem = testItem;
     }

    @Override
    public VendingMachine getItem(String itemName) {
        if (itemName.equals(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<VendingMachine> getAllItems() {
        List<VendingMachine> itemsList = new ArrayList<>();
        itemsList.add(onlyItem);
        return itemsList;
    }
    
    @Override
    public BigDecimal updateWallet(BigDecimal itemCost) {
        return itemCost;
    }

    @Override
    public void updateItems(VendingMachine item) throws IOException {      
    }

    @Override
    public void removeItem(VendingMachine item) throws IOException {
    }   
}
