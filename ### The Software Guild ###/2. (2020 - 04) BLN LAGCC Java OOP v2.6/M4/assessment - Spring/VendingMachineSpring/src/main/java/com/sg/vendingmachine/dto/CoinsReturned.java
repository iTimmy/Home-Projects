/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author Music Account
 */
public class CoinsReturned {
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private int dollarCoins;
    
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
    
    public int getDollarCoins() {
        return dollarCoins;
    }
    
    public void setDollarCoins(int dollarCoins) {
        this.dollarCoins = dollarCoins;
    }
}
