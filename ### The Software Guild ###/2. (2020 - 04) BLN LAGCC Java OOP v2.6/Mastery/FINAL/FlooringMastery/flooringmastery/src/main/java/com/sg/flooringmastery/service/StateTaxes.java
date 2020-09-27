package com.sg.flooringmastery.service;

import java.math.BigDecimal;
import java.math.MathContext;

public class StateTaxes {

    public BigDecimal fetchStateTax(String stateString) {
        return matchState(stringToState(stateString));
    }

	public BigDecimal matchState(States state) {
        MathContext mc = new MathContext(3);
        BigDecimal taxRate = new BigDecimal(0);
        
        switch(state) {
            case AL:
                taxRate = new BigDecimal(4.00).round(mc);
                return taxRate;
            case AK:
                taxRate = new BigDecimal(0.00).round(mc);
                return taxRate;
            case AZ:
                taxRate = new BigDecimal(5.60).round(mc);
                return taxRate;
            case AR:
                taxRate = new BigDecimal(6.50).round(mc);
                return taxRate;
            case CA:
                taxRate = new BigDecimal(7.25).round(mc);
                return taxRate;
            case CO:
                taxRate = new BigDecimal(2.90).round(mc);
                return taxRate;
            case CT:
                taxRate = new BigDecimal(6.35).round(mc);
                return taxRate;
            case DE:
                taxRate = new BigDecimal(0.00).round(mc);
                return taxRate;
            case FL:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case GA:
                taxRate = new BigDecimal(4.00).round(mc);
                return taxRate;
            case HI:
                taxRate = new BigDecimal(4.00).round(mc);
                return taxRate;
            case ID:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case IL:
                taxRate = new BigDecimal(6.25).round(mc);
                return taxRate;
            case IN:
                taxRate = new BigDecimal(7.00).round(mc);
                return taxRate;
            case IA:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case KS:
                taxRate = new BigDecimal(6.50).round(mc);
                return taxRate;
            case KY:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case LA:
                taxRate = new BigDecimal(4.45).round(mc);
                return taxRate;
            case ME:
                taxRate = new BigDecimal(5.50).round(mc);
                return taxRate;
            case MD:
                taxRate = new BigDecimal(6.25).round(mc);
                return taxRate;
            case MA:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case MI:
                taxRate = new BigDecimal(6.00).round(mc);
                return taxRate;
            case MN:
                taxRate = new BigDecimal(6.875).round(mc);
                return taxRate;
            case MS:
                taxRate = new BigDecimal(7.00).round(mc);
                return taxRate;
            case MO:
                taxRate = new BigDecimal(4.225).round(mc);
                return taxRate;
            case MT:
                taxRate = new BigDecimal(0.00).round(mc);
                return taxRate;
            case NE:
                taxRate = new BigDecimal(4.75).round(mc);
                return taxRate;
            case NV:
                roundBigDecimal(taxRate = new BigDecimal(5.00));
                return taxRate;
            case NH:
                roundBigDecimal(taxRate = new BigDecimal(0.00));
                return taxRate;
            case NJ:
                roundBigDecimal(taxRate = new BigDecimal(6.625));
                return taxRate;
            case NM:
                roundBigDecimal(taxRate = new BigDecimal(5.125));
                return taxRate;
            case NY:
                roundBigDecimal(taxRate = new BigDecimal(4.00));
                return taxRate;
            case NC:
                roundBigDecimal(taxRate = new BigDecimal(5.50));
                return taxRate;
            case ND:
                roundBigDecimal(taxRate = new BigDecimal(6.85));
                return taxRate;
            case OH:
                roundBigDecimal(taxRate = new BigDecimal(5.75));
                return taxRate;
            case OK:
                roundBigDecimal(taxRate = new BigDecimal(4.50));
                return taxRate;
            case OR:
                roundBigDecimal(taxRate = new BigDecimal(0.00));
                return taxRate;
            case PA:
                roundBigDecimal(taxRate = new BigDecimal(6.00));
                return taxRate;
            case RI:
                roundBigDecimal(taxRate = new BigDecimal(7.00));
                return taxRate;
            case SC:
                roundBigDecimal(taxRate = new BigDecimal(6.00));
                return taxRate;
            case SD:
                roundBigDecimal(taxRate = new BigDecimal(4.50));
                return taxRate;
            case TN:
                roundBigDecimal(taxRate = new BigDecimal(7.00));
                return taxRate;
            case TX:
                roundBigDecimal(taxRate = new BigDecimal(6.25));
                return taxRate;
            case UT:
                roundBigDecimal(taxRate = new BigDecimal(6.10));
                return taxRate;
            case VT:
                roundBigDecimal(taxRate = new BigDecimal(5.30));
                return taxRate;
            case VA:
                roundBigDecimal(taxRate = new BigDecimal(6.00));
                return taxRate;
            case WA:
                roundBigDecimal(taxRate = new BigDecimal(6.00));
                return taxRate;
            case WV:
                roundBigDecimal(taxRate = new BigDecimal(6.50));
                return taxRate;
            case WI:
                roundBigDecimal(taxRate = new BigDecimal(5.00));
                return taxRate;
            case WY:
                roundBigDecimal(taxRate = new BigDecimal(4.00));
                return taxRate;
            default:
                throw new UnsupportedOperationException("Unknown State");
        }
    }

    public States stringToState(String stateString) {
        switch (stateString) {
            case "AL":
                return States.AL;
            case "AK":
                return States.AK;
            case "AZ":
                return States.AZ;
            case "AR":
                return States.AR;
            case "CA":
                return States.CA;
            case "CO":
                return States.CO;
            case "CT":
                return States.CT;
            case "DE":
                return States.DE;
            case "FL":
                return States.FL;
            case "GA":
                return States.GA;
            case "HI":
                return States.HI;
            case "ID":
                return States.ID;
            case "IL":
                return States.IL;
            case "IN":
                return States.IN;
            case "IA":
                return States.IA;
            case "KS":
                return States.KS;
            case "KY":
                return States.KY;
            case "LA":
                return States.LA;
            case "ME":
                return States.ME;
            case "MD":
                return States.MD;
            case "MA":
                return States.MA;
            case "MI":
                return States.MI;
            case "MN":
                return States.MN;
            case "MS":
                return States.MS;
            case "MO":
                return States.MO;
            case "MT":
                return States.MT;
            case "NE":
                return States.NE;
            case "NV":
                return States.NV;
            case "NH":
                return States.NH;
            case "NJ":
                return States.NJ;
            case "NM":
                return States.NM;
            case "NY":
                return States.NY;
            case "NC":
                return States.NC;
            case "ND":
                return States.ND;
            case "OH":
                return States.OH;
            case "OK":
                return States.OK;
            case "OR":
                return States.OR;
            case "PA":
                return States.PA;
            case "RI":
                return States.RI;
            case "SC":
                return States.SC;
            case "SD":
                return States.SD;
            case "TN":
                return States.TN;
            case "TX":
                return States.TX;
            case "UT":
                return States.UT;
            case "VT":
                return States.VT;
            case "VA":
                return States.VA;
            case "WA":
                return States.WA;
            case "WV":
                return States.WV;
            case "WI":
                return States.WI;
            case "WY":
                return States.WY;
            default:
                throw new UnsupportedOperationException("Please put in a valid state.");
                // return States.INVALID;
        }
    }

    private BigDecimal roundBigDecimal(BigDecimal decimal) {
        int countPlaces = String.valueOf(decimal).length() + 2;

        MathContext mc = new MathContext(countPlaces);
        BigDecimal roundedBigDecimal = decimal.round(mc);

        return roundedBigDecimal;
    }
}