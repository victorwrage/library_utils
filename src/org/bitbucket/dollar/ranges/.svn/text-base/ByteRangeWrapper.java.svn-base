package org.bitbucket.dollar.ranges;

/**
 * @author Adam L. Davis
 * 
 */
public class ByteRangeWrapper extends AbstractNumberRangeWrapper<Byte> {

    public ByteRangeWrapper(byte stopAt) {
        super(stopAt < 0 ? stopAt : (byte) 0, stopAt < 0 ? (byte) 0 : stopAt,
                (byte) 1);
    }

    public ByteRangeWrapper(byte startFrom, byte stopAt) {
        super(startFrom > stopAt ? (byte) (startFrom - 1) : startFrom,
                startFrom > stopAt ? (byte) (stopAt - 1) : stopAt,
                startFrom > stopAt ? (byte) (-1) : (byte) 1);
    }

    public ByteRangeWrapper(Byte startFrom, Byte stopAt, Byte stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<Byte> create(Byte startFrom,
            Byte stopAt, Byte stepBy) {
        return new ByteRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected Byte add(Byte x, Byte y) {
        return (byte) (x + y);
    }

    @Override
    protected Byte floorDivide(Byte x, Byte y) {
        return (byte) (x / y);
    }

    @Override
    protected Byte multiply(Byte x, Byte y) {
        return (byte) (x * y);
    }

    @Override
    protected Byte abs(Byte x) {
        return (byte) Math.abs(x);
    }

    @Override
    protected Byte fromInt(int x) {
        return (byte) x;
    }

    @Override
    protected Byte mod(Byte number, Byte modder) {
        return new Byte((byte) (number % modder));
    }

}
