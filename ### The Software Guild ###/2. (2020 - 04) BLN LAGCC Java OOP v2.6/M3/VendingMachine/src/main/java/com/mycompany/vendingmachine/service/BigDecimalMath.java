package com.mycompany.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalMath {
    public BigDecimal calculate(MathOperator operator, BigDecimal operand1, BigDecimal itemCost) {
        switch (operator) {
            case PLUS:
                return operand1.add(itemCost);
            case MINUS:
                return operand1.subtract(itemCost);
            case MULTIPLY:
                return operand1.multiply(itemCost);
            case DIVIDE:
                return operand1.divide(itemCost, 2, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedOperationException("Unknown Math Operator");
        }
    }
}