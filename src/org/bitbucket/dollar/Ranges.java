package org.bitbucket.dollar;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.bitbucket.dollar.Dollar.RangeWrapper;
import org.bitbucket.dollar.ranges.BigDecimalRangeWrapper;
import org.bitbucket.dollar.ranges.BigIntegerRangeWrapper;
import org.bitbucket.dollar.ranges.ByteRangeWrapper;
import org.bitbucket.dollar.ranges.CharRangeWrapper;
import org.bitbucket.dollar.ranges.DateRangeWrapper;
import org.bitbucket.dollar.ranges.IntRangeWrapper;
import org.bitbucket.dollar.ranges.LongRangeWrapper;
import org.bitbucket.dollar.ranges.ShortRangeWrapper;

/**
 * Explicit methods for creating ranges.
 * 
 * @author Adam L. Davis
 * 
 */
public class Ranges {

    /** Range of integers, from 0, exclusive of stopAt. */
    public static RangeWrapper<Integer> upto(int stopAt) {
        return new IntRangeWrapper(stopAt);
    }

    /** Range of integers, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<Integer> range(int startFrom, int stopAt) {
        return new IntRangeWrapper(startFrom, stopAt);
    }

    /** Range of integers using step, like 5,10,15,20. */
    public static RangeWrapper<Integer> rangeWithStep(int startFrom,
            int stopAt, int stepBy) {
        return new IntRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of bytes, from 0, exclusive of stopAt. */
    public static RangeWrapper<Byte> upto(byte stopAt) {
        return new ByteRangeWrapper(stopAt);
    }

    /** Range of bytes, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<Byte> range(byte startFrom, byte stopAt) {
        return new ByteRangeWrapper(startFrom, stopAt);
    }

    /** Range of bytes using step. */
    public static RangeWrapper<Byte> rangeWithStep(byte startFrom, byte stopAt,
            byte stepBy) {
        return new ByteRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of shorts, from 0, exclusive of stopAt. */
    public static RangeWrapper<Short> upto(short stopAt) {
        return new ShortRangeWrapper(stopAt);
    }

    /** Range of shorts, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<Short> range(short startFrom, short stopAt) {
        return new ShortRangeWrapper(startFrom, stopAt);
    }

    /** Range of shorts using step. */
    public static RangeWrapper<Short> rangeWithStep(short startFrom,
            short stopAt, short stepBy) {
        return new ShortRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of longs, from 0, exclusive of stopAt. */
    public static RangeWrapper<Long> upto(long stopAt) {
        return new LongRangeWrapper(stopAt);
    }

    /** Range of longs, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<Long> range(long startFrom, long stopAt) {
        return new LongRangeWrapper(startFrom, stopAt);
    }

    /** Range of longs using step. */
    public static RangeWrapper<Long> rangeWithStep(long startFrom, long stopAt,
            long stepBy) {
        return new LongRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of BigDecimals, from 0, exclusive of stopAt. */
    public static RangeWrapper<BigDecimal> upto(BigDecimal stopAt) {
        return new BigDecimalRangeWrapper(stopAt);
    }

    /** Range of BigDecimals, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<BigDecimal> range(BigDecimal startFrom,
            BigDecimal stopAt) {
        return new BigDecimalRangeWrapper(startFrom, stopAt);
    }

    /** Range of BigDecimals using step. */
    public static RangeWrapper<BigDecimal> rangeWithStep(BigDecimal startFrom,
            BigDecimal stopAt, BigDecimal stepBy) {
        return new BigDecimalRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of BigIntegers, from 0, exclusive of stopAt. */
    public static RangeWrapper<BigInteger> upto(BigInteger stopAt) {
        return new BigIntegerRangeWrapper(stopAt);
    }

    /** Range of BigIntegers, from startFrom, exclusive of stopAt. */
    public static RangeWrapper<BigInteger> range(BigInteger startFrom,
            BigInteger stopAt) {
        return new BigIntegerRangeWrapper(startFrom, stopAt);
    }

    /** Range of BigIntegers using step. */
    public static RangeWrapper<BigInteger> rangeWithStep(BigInteger startFrom,
            BigInteger stopAt, BigInteger stepBy) {
        return new BigIntegerRangeWrapper(startFrom, stopAt, stepBy);
    }

    /** Range of characters, like A-Z. */
    public static RangeWrapper<Character> range(char from, char to) {
        return new CharRangeWrapper(from, to);
    }

    /**
     * Date Range with default of step=1 and field=day.
     * 
     * @param begin
     *            Start date, inclusive.
     * @param end
     *            Ending date, exclusive.
     * @return new DateRangeWrapper using given values.
     */
    public static RangeWrapper<Date> range(Date begin, Date end) {
        return new DateRangeWrapper(begin, end);
    }

    /**
     * Date Range with default of field=day.
     * 
     * @param begin
     *            Start date, inclusive.
     * @param end
     *            Ending date, exclusive.
     * @param step
     *            Number of units added/subtracted each iteration (not zero).
     * @return new DateRangeWrapper using given values.
     */
    public static RangeWrapper<Date> rangeWithStep(Date begin, Date end,
            int step) {
        return new DateRangeWrapper(begin, end, step);
    }

    /**
     * Date Range with given values.
     * 
     * @param begin
     *            Start date, inclusive.
     * @param end
     *            Ending date, exclusive.
     * @param step
     *            Number of units added/subtracted each iteration (not zero).
     * @param field
     *            Calendar field to increase each iteration.
     * @return new DateRangeWrapper using given values.
     * @see java.util.Calendar
     */
    public static RangeWrapper<Date> rangeWithStepAndField(final Date begin,
            final Date end, final int step, final int field) {
        return new DateRangeWrapper(begin, end, step, field);
    }

    /**
     * Date Range with given values using enum for date field.
     * 
     * @param begin
     *            Start date, inclusive.
     * @param end
     *            Ending date, exclusive.
     * @param step
     *            Number of units added/subtracted each iteration (not zero).
     * @param field
     *            field to increase each iteration.
     * @return new DateRangeWrapper using given values.
     * @see DateField
     */
    public static RangeWrapper<Date> rangeWithStepAndField(final Date begin,
            final Date end, final int step, final DateField field) {
        return new DateRangeWrapper(begin, end, step, field.calendarField);
    }

    /**
     * DSL for building a DateRangeWrapper.
     * 
     * @param begin
     *            Beginning date of range, inclusive.
     * @param end
     *            Ending date of range, exclusive.
     * @return new builder.
     */
    public static DateRangeBuilder1 builder(Date begin, Date end) {
        return new DateRangeBuilder1(begin, end);
    }

    public static class DateRangeBuilder1 {
        final Date begin;
        final Date end;

        private DateRangeBuilder1(Date begin, Date end) {
            super();
            this.begin = begin;
            this.end = end;
        }

        /** Number of units added/subtracted each iteration (not zero). */
        public DateRangeBuilder2 step(int step) {
            return new DateRangeBuilder2(begin, end, step);
        }

        public RangeWrapper<Date> build() {
            return range(begin, end);
        }
    }

    public static class DateRangeBuilder2 extends DateRangeBuilder1 {
        final int step;

        private DateRangeBuilder2(Date begin, Date end, int step) {
            super(begin, end);
            this.step = step;
        }

        /** DateField to increase each iteration. */
        public DateRangeBuilder3 field(DateField field) {
            return new DateRangeBuilder3(begin, end, step, field);
        }

        @Override
        public RangeWrapper<Date> build() {
            return rangeWithStep(begin, end, step);
        }
    }

    public static class DateRangeBuilder3 extends DateRangeBuilder2 {
        final DateField field;

        private DateRangeBuilder3(Date begin, Date end, int step,
                DateField field) {
            super(begin, end, step);
            this.field = field;
        }

        @Override
        public RangeWrapper<Date> build() {
            return rangeWithStepAndField(begin, end, step, field);
        }
    }

}
