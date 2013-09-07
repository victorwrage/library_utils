package org.bitbucket.dollar.ranges;

/**
 * @author Adam L. Davis
 * 
 */
public class ShortRangeWrapper extends AbstractNumberRangeWrapper<Short> {

    public ShortRangeWrapper(short stopAt) {
        super(stopAt < 0 ? stopAt : (short) 0, stopAt < 0 ? (short) 0 : stopAt,
                (short) 1);
    }

    public ShortRangeWrapper(short startFrom, short stopAt) {
        super(startFrom > stopAt ? (short) (startFrom - 1) : startFrom,
                startFrom > stopAt ? (short) (stopAt - 1) : stopAt,
                startFrom > stopAt ? (short) (-1) : (short) 1);
    }

    public ShortRangeWrapper(Short startFrom, Short stopAt, Short stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<Short> create(Short startFrom,
            Short stopAt, Short stepBy) {
        return new ShortRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected Short add(Short x, Short y) {
        return (short) (x + y);
    }

    @Override
    protected Short floorDivide(Short x, Short y) {
        return (short) (x / y);
    }

    @Override
    protected Short multiply(Short x, Short y) {
        return (short) (x * y);
    }

    @Override
    protected Short abs(Short x) {
        return (short) Math.abs(x);
    }

    @Override
    protected Short fromInt(int x) {
        return (short) x;
    }

    @Override
    protected Short mod(Short number, Short modder) {
        return (short) (number % modder);
    }
}
