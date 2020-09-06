package service;

import java.math.BigDecimal;

public class StateTaxes {

	public BigDecimal stateSelect(States state, String stateShort) {
        BigDecimal taxRate = new BigDecimal(0);
        
        switch(state) {
            case AL:
                taxRate = new BigDecimal(4.00);
                return taxRate;
            case AK:
                taxRate = new BigDecimal(0.00);
            case AZ:
                taxRate = new BigDecimal(5.60);
            case AR:
                taxRate = new BigDecimal(6.50);
            case CA:
                taxRate = new BigDecimal(7.25);
            case CO:
                taxRate = new BigDecimal(2.90);
            case CT:
                taxRate = new BigDecimal(6.35);
            case DE:
                taxRate = new BigDecimal(0.00);
            case FL:
                taxRate = new BigDecimal(6.00);
            case GA:
                taxRate = new BigDecimal(4.00);
            case HI:
                taxRate = new BigDecimal(4.00);
            case ID:
                taxRate = new BigDecimal(6.00);
            case IL:
                taxRate = new BigDecimal(6.25);
            case IN:
                taxRate = new BigDecimal(7.00);
            case IA:
                taxRate = new BigDecimal(6.00);
            case KS:
                taxRate = new BigDecimal(6.50);
            case KY:
                taxRate = new BigDecimal(6.00);
            case LA:
                taxRate = new BigDecimal(4.45);
            case ME:
                taxRate = new BigDecimal(5.50);
            case MD:
                taxRate = new BigDecimal(6.25);
            case MA:
                taxRate = new BigDecimal(6.00);
            case MI:
                taxRate = new BigDecimal(6.00);
            case MN:
                taxRate = new BigDecimal(6.875);
            case MS:
                taxRate = new BigDecimal(7.00);
            case MO:
                taxRate = new BigDecimal(4.225);
            case MT:
                taxRate = new BigDecimal(0.00);
            case NE:
                taxRate = new BigDecimal(4.75);
            case NV:
                taxRate = new BigDecimal(5.00);
            case NH:
                taxRate = new BigDecimal(0.00);
            case NJ:
                taxRate = new BigDecimal(6.625);
            case NM:
                taxRate = new BigDecimal(5.125);
            case NY:
                taxRate = new BigDecimal(4.00);
            case NC:
                taxRate = new BigDecimal(5.50);
            case ND:
                taxRate = new BigDecimal(6.85);
            case OH:
                taxRate = new BigDecimal(5.75);
            case OK:
                taxRate = new BigDecimal(4.50);
            case OR:
                taxRate = new BigDecimal(0.00);
            case PA:
                taxRate = new BigDecimal(6.00);
            case RI:
                taxRate = new BigDecimal(7.00);
            case SC:
                taxRate = new BigDecimal(6.00);
            case SD:
                taxRate = new BigDecimal(4.50);
            case TN:
                taxRate = new BigDecimal(7.00);
            case TX:
                taxRate = new BigDecimal(6.25);
            case UT:
                taxRate = new BigDecimal(6.10);
            case VT:
                taxRate = new BigDecimal(5.30);
            case VA:
                taxRate = new BigDecimal(6.00);
            case WA:
                taxRate = new BigDecimal(6.00);
            case WV:
                taxRate = new BigDecimal(6.50);
            case WI:
                taxRate = new BigDecimal(5.00);
            case WY:
                taxRate = new BigDecimal(4.00);
            default:
                throw new UnsupportedOperationException("Unknown State");
        }
    }
}
