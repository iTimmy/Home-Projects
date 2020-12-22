package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import com.sg.flooringmastery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {
    
    @Autowired
    Map<String, Tax> storeTax = new HashMap<>();
    private String file = "Data/Taxes/Taxes.txt";
    File fileTaxes = new File(file);
    
//    @Autowired
//    public FlooringMasteryTaxDaoImpl() {
//        this.file = "Data/Taxes/Taxes.txt";
//    }
//    public FlooringMasteryTaxDaoImpl(String textFile) {
//        this.file = textFile;
//    }

    @Override
    public List<Tax> getAllTaxes() {
        try {
            loadTaxes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tax> listTaxes = new ArrayList<Tax>(storeTax.values());
        return listTaxes;
    }

    @Override
    public Tax getTaxByState(String state) {
        try {
            if (storeTax.isEmpty() == true) {
                loadTaxes();
            }
        } catch (Exception e) {
            System.out.println("Error.");
        }
        return storeTax.get(state);
    }

    private void loadTaxes() throws Exception {
        Scanner readFile = new Scanner(new BufferedReader(new FileReader(fileTaxes)));
        String currentLine = "";
        while(readFile.hasNextLine()) {
            currentLine = readFile.nextLine();
            Tax currentTax = unmarshallTaxes(currentLine);
            storeTax.put(currentTax.getState(), currentTax);
        }
    }
    private Tax unmarshallTaxes(String currentLine) {
        String[] taxTokens = currentLine.split(",");
        String state = taxTokens[0];
        String stateName = taxTokens[1];
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);

        Tax tax = new Tax();
        tax.setState(state);
        tax.setTaxRate(taxRate);
        
        return tax;
    }
}
