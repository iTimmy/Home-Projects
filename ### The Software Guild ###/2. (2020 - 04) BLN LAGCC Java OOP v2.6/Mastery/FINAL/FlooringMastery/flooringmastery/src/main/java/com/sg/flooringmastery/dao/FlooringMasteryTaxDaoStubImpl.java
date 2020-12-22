<<<<<<< Updated upstream
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Music Account
 */
@Component
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {
    
    public Tax onlyTax;
    MathContext mc = new MathContext(4);

    @Autowired
    public FlooringMasteryTaxDaoStubImpl() {
        onlyTax = new Tax();
        onlyTax.setState("TX");
        onlyTax.setTaxRate(new BigDecimal(2.25).round(mc));
    }

    public FlooringMasteryTaxDaoStubImpl(Tax testTax){
         this.onlyTax = testTax;
     }
    
    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> listTaxes = new ArrayList<>();
        listTaxes.add(onlyTax);
        return listTaxes;
    }
    
    @Override
    public Tax getTaxByState(String state) {
        System.out.println("stub: " + onlyTax.getState());
        if (state.toUpperCase().equals(onlyTax.getState().toUpperCase())) {
            return onlyTax;
        } else {
            return null;
        }  
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Music Account
 */
@Component
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {
    
    public Tax onlyTax;

    @Autowired
    public FlooringMasteryTaxDaoStubImpl() {
        onlyTax = new Tax();
        onlyTax.setState("TX");
    }

    public FlooringMasteryTaxDaoStubImpl(Tax testTax){
         this.onlyTax = testTax;
     }
    
    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> listTaxes = new ArrayList<>();
        listTaxes.add(onlyTax);
        return listTaxes;
    }
    
    @Override
    public Tax getTaxByState(String state) {
        System.out.println("stub: " + onlyTax.getState());
        if (state.toUpperCase().equals(onlyTax.getState().toUpperCase())) {
            return onlyTax;
        } else {
            return null;
        }  
    }
    
}
>>>>>>> Stashed changes
