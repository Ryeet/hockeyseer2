package fi.hockeyseer.service.calculations;

import java.math.BigDecimal;
import java.math.MathContext;

public final class BDCalc {

    public static BigDecimal sum(BigDecimal one, BigDecimal two) {
        one = one.add(two, MathContext.DECIMAL32);
        return one;
    }

    public static BigDecimal multiply(BigDecimal one, Double two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.multiply(new BigDecimal(two), MathContext.DECIMAL32);
        return one;
    }

    public static BigDecimal multiply(BigDecimal one, Integer two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.multiply(new BigDecimal(two), MathContext.DECIMAL32);
        return one;
    }

    public static BigDecimal multiply(BigDecimal one, BigDecimal two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.multiply(two, MathContext.DECIMAL32);
        return one;
    }

    public static BigDecimal divide(BigDecimal one, Double two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.divide(new BigDecimal(two, MathContext.DECIMAL32), MathContext.DECIMAL32);
        return one;
    }

    public static BigDecimal divide(BigDecimal one, Integer two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.divide(new BigDecimal(two, MathContext.DECIMAL32), MathContext.DECIMAL32);
        return one;
    }
    public static BigDecimal divide(BigDecimal one, BigDecimal two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");
        one = one.divide(two, MathContext.DECIMAL32);
        return one;
    }


    /*

        public static BigDecimal multiply(BigDecimal one, Double two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");

        if (two instanceof Integer) {
            one = one.multiply(new BigDecimal((int)(T) two), MathContext.DECIMAL32);
            return one;

        } else if (two instanceof Double) {
            one = one.multiply(new BigDecimal(((double)(T) two)), MathContext.DECIMAL32);
            return one;
        } else if (two instanceof BigDecimal) {
            one = one.multiply((BigDecimal) two, MathContext.DECIMAL32);
            return one;
        } else {
            throw new UnsupportedOperationException();
        }
    }
     */

    /*
      public static <T extends Number> BigDecimal divide(BigDecimal one, T two) {
        if (one == BigDecimal.ZERO) throw new IllegalArgumentException("First argument is 0");

        if (two instanceof Integer) {
            one = one.divide(new BigDecimal((int) two), MathContext.DECIMAL32);
            return one;
        } else if (two instanceof Double) {
            one = one.divide(new BigDecimal((double) two), MathContext.DECIMAL32);
            return one;
        } else if (two instanceof BigDecimal) {
            one = one.divide((BigDecimal) two, MathContext.DECIMAL32);
            return one;
        } else {
            throw new UnsupportedOperationException();
        }

    }

     */

}
