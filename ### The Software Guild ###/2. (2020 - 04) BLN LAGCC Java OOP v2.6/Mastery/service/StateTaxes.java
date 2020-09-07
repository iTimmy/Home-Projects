package service;

import java.math.BigDecimal;

public class StateTaxes {

    public BigDecimal fetchStateTax(String stateString) {
        return matchState(stringToState(stateString));
    }

	public BigDecimal matchState(States state) {
        
        BigDecimal taxRate = new BigDecimal(0);
        
        switch(state) {
            case AL:
                taxRate = new BigDecimal(4.00);
                return taxRate;
            case AK:
                taxRate = new BigDecimal(0.00);
                return taxRate;
            case AZ:
                taxRate = new BigDecimal(5.60);
                return taxRate;
            case AR:
                taxRate = new BigDecimal(6.50);
                return taxRate;
            case CA:
                taxRate = new BigDecimal(7.25);
                return taxRate;
            case CO:
                taxRate = new BigDecimal(2.90);
                return taxRate;
            case CT:
                taxRate = new BigDecimal(6.35);
                return taxRate;
            case DE:
                taxRate = new BigDecimal(0.00);
                return taxRate;
            case FL:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case GA:
                taxRate = new BigDecimal(4.00);
                return taxRate;
            case HI:
                taxRate = new BigDecimal(4.00);
                return taxRate;
            case ID:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case IL:
                taxRate = new BigDecimal(6.25);
                return taxRate;
            case IN:
                taxRate = new BigDecimal(7.00);
                return taxRate;
            case IA:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case KS:
                taxRate = new BigDecimal(6.50);
                return taxRate;
            case KY:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case LA:
                taxRate = new BigDecimal(4.45);
                return taxRate;
            case ME:
                taxRate = new BigDecimal(5.50);
                return taxRate;
            case MD:
                taxRate = new BigDecimal(6.25);
                return taxRate;
            case MA:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case MI:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case MN:
                taxRate = new BigDecimal(6.875);
                return taxRate;
            case MS:
                taxRate = new BigDecimal(7.00);
                return taxRate;
            case MO:
                taxRate = new BigDecimal(4.225);
                return taxRate;
            case MT:
                taxRate = new BigDecimal(0.00);
                return taxRate;
            case NE:
                taxRate = new BigDecimal(4.75);
                return taxRate;
            case NV:
                taxRate = new BigDecimal(5.00);
                return taxRate;
            case NH:
                taxRate = new BigDecimal(0.00);
                return taxRate;
            case NJ:
                taxRate = new BigDecimal(6.625);
                return taxRate;
            case NM:
                taxRate = new BigDecimal(5.125);
                return taxRate;
            case NY:
                taxRate = new BigDecimal(4.00);
                return taxRate;
            case NC:
                taxRate = new BigDecimal(5.50);
                return taxRate;
            case ND:
                taxRate = new BigDecimal(6.85);
                return taxRate;
            case OH:
                taxRate = new BigDecimal(5.75);
                return taxRate;
            case OK:
                taxRate = new BigDecimal(4.50);
                return taxRate;
            case OR:
                taxRate = new BigDecimal(0.00);
                return taxRate;
            case PA:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case RI:
                taxRate = new BigDecimal(7.00);
                return taxRate;
            case SC:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case SD:
                taxRate = new BigDecimal(4.50);
                return taxRate;
            case TN:
                taxRate = new BigDecimal(7.00);
                return taxRate;
            case TX:
                taxRate = new BigDecimal(6.25);
                return taxRate;
            case UT:
                taxRate = new BigDecimal(6.10);
                return taxRate;
            case VT:
                taxRate = new BigDecimal(5.30);
                return taxRate;
            case VA:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case WA:
                taxRate = new BigDecimal(6.00);
                return taxRate;
            case WV:
                taxRate = new BigDecimal(6.50);
                return taxRate;
            case WI:
                taxRate = new BigDecimal(5.00);
                return taxRate;
            case WY:
                taxRate = new BigDecimal(4.00);
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
}