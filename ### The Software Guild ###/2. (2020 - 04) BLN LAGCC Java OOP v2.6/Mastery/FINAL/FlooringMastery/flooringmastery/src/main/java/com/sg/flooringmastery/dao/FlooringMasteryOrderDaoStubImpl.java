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
    public Order createOrder(Order order) throws Exception {
        return order.getOrderDate().equals(onlyOrder.getOrderDate()) ? onlyOrder : null;
    }
    
    @Override
    public List<Order> getOrdersByDate(LocalDate userInputDate) throws Exception {
        List<Order> listOrders = new ArrayList<>();
        listOrders.add(onlyOrder);
        return listOrders;
    }

    @Override
    public Order getOrderByID(int orderNumber) {
        return orderNumber == onlyOrder.getOrderNumber() ? onlyOrder : null;
    }
    
    @Override
    public void updateOrder(Order editedOrder, Order existingOrder) throws Exception {
            existingOrder.setCustomerName(editedOrder.getCustomerName());
            existingOrder.setArea(editedOrder.getArea());
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        if (order.getOrderNumber() == (onlyOrder.getOrderNumber())) {
                onlyOrder.setCustomerName("");
        }
    }   
    
    @Override
    public boolean saveOrdersByDate() throws Exception {
        return false;
    }

}
