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
        List<Tax> listTaxes = new ArrayList<Tax>(storeTax.values());
        return listTaxes;
    }

    @Override
    public Tax getTaxByState(String state) {
        try {
            if (storeTax.isEmpty() == true) {
                loadTaxes();
            } else if (storeTax.isEmpty() == false) {
                storeTax.get(state);
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
            storeTax.put(currentLine, currentTax);
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
