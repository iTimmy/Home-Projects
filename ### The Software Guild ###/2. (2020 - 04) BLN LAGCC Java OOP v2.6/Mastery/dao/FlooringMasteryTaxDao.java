package dao;

import java.math.BigDecimal;
import java.util.*;
import dto.*;

public interface FlooringMasteryTaxDao {
    List<Tax> getAllTaxes();
    Tax getTaxByFloat(BigDecimal tax);
}
