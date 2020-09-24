/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author timmy
 */
public class UserWallet {
    private BigDecimal money;
    private int requestItemQuantity = 1;
    
    public BigDecimal getMoney() {
        return money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public int getRequestItemQuantity() {
        return requestItemQuantity;
    }
    
    public void setRequestItemQuantity(int requestItemQuantity) {
        this.requestItemQuantity = requestItemQuantity;
    }
}
