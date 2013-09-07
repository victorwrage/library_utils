package org.bitbucket.dollar.ranges;

/**
 * @author Adam L. Davis
 * 
 */
public class LongRangeWrapper extends AbstractNumberRangeWrapper<Long> {

    public LongRangeWrapper(long stopAt) {
        super(stopAt < 0 ? stopAt : (long) 0, stopAt < 0 ? (long) 0 : stopAt,
                (long) 1);
    }

    public LongRangeWrapper(long startFrom, long stopAt) {
        super(startFrom > stopAt ? (long) (startFrom - 1) : startFrom,
                startFrom > stopAt ? (long) (stopAt - 1) : stopAt,
                startFrom > stopAt ? (long) (-1) : (long) 1);
    }

    public LongRangeWrapper(Long startFrom, Long stopAt, Long stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<Long> create(Long startFrom,
            Long stopAt, Long stepBy) {
        return new LongRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected Long add(Long x, Long y) {
        return (x + y);
    }

    @Override
    protected Long floorDivide(Long x, Long y) {
        return (x / y);
    }

    @Override
    protected Long multiply(Long x, Long y) {
        return (x * y);
    }

    @Override
    protected Long abs(Long x) {
        return Math.abs(x);
    }

    @Override
    protected Long fromInt(int x) {
        return (long) x;
    }

    @Override
    protected Long mod(Long number, Long modder) {
        return number.longValue() % modder.longValue();
    }

}
