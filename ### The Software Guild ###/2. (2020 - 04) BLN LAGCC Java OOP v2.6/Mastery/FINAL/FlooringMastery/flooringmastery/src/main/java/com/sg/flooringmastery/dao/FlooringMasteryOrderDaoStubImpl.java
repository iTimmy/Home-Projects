/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.service.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryOrderDao {
    public Order onlyOrder;
    MathContext mc = new MathContext(2);
    BigDecimalMath mathCalculate = new BigDecimalMath();

    @Autowired
    public FlooringMasteryOrderDaoStubImpl() {
        onlyOrder = new Order();
        onlyOrder.setCustomerName("Ada");
        onlyOrder.setOrderNumber(1);
        onlyOrder.setOrderDate(LocalDate.of(2030, Month.DECEMBER, 5));
        onlyOrder.setArea(new BigDecimal(233).round(mc));
    }

    public FlooringMasteryOrderDaoStubImpl(Order testOrder){
         this.onlyOrder = testOrder;
     }

    @Override
    public Order createOrder(Order order)
                  throws FlooringMasteryPersistenceException {
        if (order.getOrderDate().equals(onlyOrder.getOrderDate())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getActiveOrders() {
        List<Order> activeOrders = new ArrayList<>();
        activeOrders.add(onlyOrder);
        return activeOrders;
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate)
                 throws FlooringMasteryPersistenceException {
        List<Order> listOrders = new ArrayList<>();
        listOrders.add(onlyOrder);
        return listOrders;
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }       
    }
    
    @Override
    public void updateOrder(Order editedOrder, Order existingOrder) throws FlooringMasteryPersistenceException {
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
    }

    @Override
    public void deleteOrder(Order order)
                throws FlooringMasteryPersistenceException {
        if (order.getOrderNumber() == (onlyOrder.getOrderNumber())) {
                onlyOrder.setCustomerName("");
        }
    }   
    
    @Override
    public boolean saveOrdersByDate() throws FlooringMasteryPersistenceException {
        return false;
    }
}
