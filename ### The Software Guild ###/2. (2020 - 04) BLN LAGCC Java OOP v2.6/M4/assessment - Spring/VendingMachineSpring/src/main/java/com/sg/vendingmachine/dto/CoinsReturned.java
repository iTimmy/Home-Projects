/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Music Account
 */
public class CoinsReturned {
    private BigDecimal change;
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private String statement;
    private String returnedCoins;
    
    public String getReturnedCoins() {
        return returnedCoins;
    }
    
    public void setReturnedCoins(String returnedCoins) {
        this.returnedCoins = returnedCoins;
    }
    
    public String getStatement() {
        return statement;
    }
    
    public void setStatement(String statement) {
        this.statement = statement;
    }
    
    public BigDecimal getChange() {
        return change;
    }
    
    public void setChange(BigDecimal change) {
        this.change = change;
    }
    
    public int getPennies() {
        return pennies;
    }
    
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
    
    public int getNickels() {
        return nickels;
    }
    
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }
    
    public int getDimes() {
        return dimes;
    }
    
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }
    
    public int getQuarters() {
        return quarters;
    }
    
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }
}
