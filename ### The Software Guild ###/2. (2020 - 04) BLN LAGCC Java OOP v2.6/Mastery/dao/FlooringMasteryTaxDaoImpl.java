package dao;

import java.math.BigDecimal;
import java.util.*;
import dto.*;

import java.util.*;
import dto.*;

public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    Map<BigDecimal, Tax> storeTax = new HashMap<>();
    private static final String DELIMITER = " :: ";

    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> listTaxes = new ArrayList<Tax>(storeTax.values());
        return listTaxes;
    }

    @Override
    public Tax getTaxByFloat(BigDecimal tax) {
        return storeTax.get(tax);
    }
}
