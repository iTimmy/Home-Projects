package dao;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import dto.*;

public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    Map<String, Tax> storeTax = new HashMap<>();
    File fileTaxes = new File("Data/Taxes/Taxes.txt");

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
        // System.out.println(state);
        try {
            // System.out.println("trying");
            if (storeTax.isEmpty() == true) {
                // System.out.println("tax Loading...");
                loadTaxes();
                // System.out.println(storeTax);
                // System.out.println("if not empty: " + storeTax.get(state).getState());
            }
        } catch (Exception e) {
            System.out.println("Error.");
        }
        // System.out.println("returning");
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
