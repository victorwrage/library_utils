package org.bitbucket.dollar.ranges;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;

/**
 * @author Adam L. Davis
 */
public class BigIntegerRangeWrapper extends
        AbstractNumberRangeWrapper<BigInteger> {

    public static final boolean gt(BigInteger a, BigInteger b) {
        return a.compareTo(b) > 0;
    }

    public BigIntegerRangeWrapper(BigInteger stopAt) {
        super(stopAt.compareTo(ZERO) < 0 ? stopAt : ZERO, stopAt
                .compareTo(ZERO) < 0 ? ZERO : stopAt, ONE);
    }

    public BigIntegerRangeWrapper(BigInteger startFrom, BigInteger stopAt) {
        super(gt(startFrom, stopAt) ? (startFrom.subtract(ONE)) : startFrom,
                gt(startFrom, stopAt) ? (stopAt.subtract(ONE)) : stopAt, gt(
                        startFrom, stopAt) ? (ONE.negate()) : (ONE));
    }

    public BigIntegerRangeWrapper(BigInteger startFrom, BigInteger stopAt,
            BigInteger stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<BigInteger> create(
            BigInteger startFrom, BigInteger stopAt, BigInteger stepBy) {
        return new BigIntegerRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    protected BigInteger floorDivide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    protected BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    protected BigInteger abs(BigInteger x) {
        return x.abs();
    }

    @Override
    protected BigInteger fromInt(int x) {
        if (x == 0) {
            return ZERO;
        }
        return new BigInteger(String.valueOf(x));
    }

}
