package org.bitbucket.dollar.ranges;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Adam L. Davis
 */
public class BigDecimalRangeWrapper extends
        AbstractNumberRangeWrapper<BigDecimal> {

    public static final boolean gt(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0;
    }

    public BigDecimalRangeWrapper(BigDecimal stopAt) {
        super(stopAt.compareTo(ZERO) < 0 ? stopAt : ZERO, stopAt
                .compareTo(ZERO) < 0 ? ZERO : stopAt, ONE);
    }

    public BigDecimalRangeWrapper(BigDecimal startFrom, BigDecimal stopAt) {
        super(gt(startFrom, stopAt) ? (startFrom.subtract(ONE)) : startFrom,
                gt(startFrom, stopAt) ? (stopAt.subtract(ONE)) : stopAt, gt(
                        startFrom, stopAt) ? (ONE.negate()) : (ONE));
    }

    public BigDecimalRangeWrapper(BigDecimal startFrom, BigDecimal stopAt,
            BigDecimal stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<BigDecimal> create(
            BigDecimal startFrom, BigDecimal stopAt, BigDecimal stepBy) {
        return new BigDecimalRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected BigDecimal add(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }

    // rounds using floor (so any fractions get cut off).
    @Override
    protected BigDecimal floorDivide(BigDecimal x, BigDecimal y) {
        return x.divide(y, RoundingMode.FLOOR);
    }

    @Override
    protected BigDecimal multiply(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }

    @Override
    protected BigDecimal abs(BigDecimal x) {
        return x.abs();
    }

    @Override
    protected BigDecimal fromInt(int x) {
        if (x == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(x);
    }

}
