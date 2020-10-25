package com.sg.flooringmastery.dao;

import java.util.*;
import com.sg.flooringmastery.dto.*;

public interface FlooringMasteryTaxDao {
    List<Tax> getAllTaxes();
    Tax getTaxByState(String state);
}
